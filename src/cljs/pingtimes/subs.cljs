(ns pingtimes.subs
  (:require
   [re-frame.core :as re-frame]))

(re-frame/reg-sub
 ::latency
 (fn [db [_ zone]]
   (get-in db [:ping zone :latency])))

(re-frame/reg-sub
 ::endpoint
 (fn [db [_ zone]]
   (get-in db [:endpoint zone])))

(re-frame/reg-sub
 ::zones
 (fn [db _]
   (keys (:ping db))))

(re-frame/reg-sub
 ::details
 (fn [db _]
   (when-let [zone (:detail-zone db)]
     (get-in db [:details zone]))))

(re-frame/reg-sub
 ::coords
 (fn [db _]
   (:coords db)))
