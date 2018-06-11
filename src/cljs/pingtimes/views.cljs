(ns pingtimes.views
  (:require
   [re-frame.core :as re-frame]
   [pingtimes.events :as events]
   [pingtimes.subs :as subs]))

(defn zone-latency
  [zone]
  (let [latency (re-frame/subscribe [::subs/latency zone])]
    [:div {:on-click #(re-frame/dispatch [::events/show-details zone])
           :class (str "zone zone--" (name zone))}
        [:span.zone__name
          (str (name zone) ": ")]
        [:span.zone__latency
          (str @latency " ms")]]))

(defn zone-details
  [{:keys [title content img]}]
  [:div.zone__detail {:on-click #(re-frame/dispatch [::events/remove-details])}
   [:div.zone__detail__picture
    [:img {:src (str "img/" img)
           :alt "Datacenter exterior photo"}]]
   [:div.zone__detail__body
    [:div.zone__detail__title title]
    [:div.zone__detail__description content]]])

(defn main-panel []
  (let [zones   (re-frame/subscribe [::subs/zones])
        details (re-frame/subscribe [::subs/details])]
    [:div#main
     [:div.image-wrapper
      [:div.header "Ping times from your location."]
      (for [zone @zones]
        ^{:key zone} [zone-latency zone])
      (when @details
       [zone-details @details])]]))
