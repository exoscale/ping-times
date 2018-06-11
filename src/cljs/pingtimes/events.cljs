(ns pingtimes.events
  (:require
   [re-frame.core :as re-frame]
   [ajax.core :as ajax]
   [pingtimes.db :as db]
   [day8.re-frame.tracing :refer-macros [fn-traced defn-traced]]))

(re-frame/reg-event-db
 ::initialize-db
 (fn-traced [_ _]
            db/default-db))

(re-frame/reg-event-db
 ::show-details
 (fn-traced [db [_ zone]] (assoc db :detail-zone zone)))

(re-frame/reg-event-db
 ::remove-details
 (fn-traced [db _] (dissoc db :detail-zone)))

(re-frame/reg-event-fx
 ::ping
 (fn-traced [{:keys [db]} [_ zone]]
   (let [endpoint (get-in db [:endpoints zone])
         timestamp (.getTime (js/Date.))]
     {:http-xhrio {:method :get
                   :uri endpoint
                   :timeout 1000
                   :response-format (ajax/raw-response-format)
                   :on-success [::good-http-result zone]
                   :on-failure [::error-http-result zone]}
      :db (assoc-in db [:ping zone :sent-at] timestamp)})))

(re-frame/reg-event-db
 ::good-http-result
 (fn-traced [db [_ zone]]
   (let [latency (- (.getTime (js/Date.)) (get-in db [:ping zone :sent-at]))]
     (assoc-in db [:ping zone :latency] latency))))

(re-frame/reg-event-db
 ::error-http-result
 (fn-traced [db [_ zone]]
   (assoc-in db [:ping zone :latency-error] true)))
