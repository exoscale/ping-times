(ns pingtimes.db)

(def default-db
  {:ping       {:ch-dk-2  {:latency 0 :initial? false}
                :de-fra-1 {:latency 0 :initial? false}
                :ch-gva-2 {:latency 0 :initial? false}
                :at-vie-1 {:latency 0 :initial? false}}
   :endpoints  {:ch-dk-2  "https://ping-times.sos-ch-dk-2.exo.io/index.html"
                :de-fra-1 "https://ping-times-fra.sos-de-fra-1.exo.io/index.html"
                :ch-gva-2 "https://ping-times.sos-ch-dk-2.exo.io/index.html" ;; FIXME once hosts are up
                :at-vie-1 "https://ping-times-vie.sos-at-vie-1.exo.io/index.html"}
   :detail-zone nil
   :details    {:ch-dk-2 {:title "Zurich Datacenter"
                          :content "Buried underground the Alps in a former military anti-atomic command and control center, CH-DK-2 is hosted by the Deltalis data center facility."
                          :img "dc-ch-dk-2.png"}
                :de-fra-1 {:title "Frankfurt Datacenter"
                           :content "The German DE-FRA-1 datacenter is ideally placed to serve teams looking for GDPR-compliant cloud servers. Hosted by the Equinix data center facility."
                           :img "dc-de-fra-1.png"}
                :ch-gva-2 {:title "Geneva Datacenter"
                           :content "Placed close to European financial ecosystems and global markets, CH-GV-2 is hosted by the Equinix data center facility."
                           :img "dc-ch-gva-2.png"}
                :at-vie-1 {:title "Vienna Datacenter"
                           :content "Located in the Arsenal, a former military complex of buildings, our location in Vienna follows our strict requirements for data center choices."
                           :img "dc-at-vie-1.png"}}})
