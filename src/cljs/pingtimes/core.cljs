(ns pingtimes.core
  (:require
   [reagent.core :as reagent]
   [cljs.core.async :as async]
   [re-frame.core :as re-frame]
   [day8.re-frame.http-fx]
   [pingtimes.events :as events]
   [pingtimes.subs :as subs]
   [pingtimes.views :as views]
   [pingtimes.config :as config]))

(defn dev-setup []
  (when config/debug?
    (enable-console-print!)
    (println "dev mode")))

(defn mount-root []
  (re-frame/clear-subscription-cache!)
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn ^:export init []
  (re-frame/dispatch-sync [::events/initialize-db])
  (async/go-loop []
    (let [zones @(re-frame/subscribe [::subs/zones])]
      (doseq [zone zones]
        (re-frame/dispatch [::events/ping zone]))
      (async/<! (async/timeout 2000))
      (recur)))
  (dev-setup)
  (mount-root))
