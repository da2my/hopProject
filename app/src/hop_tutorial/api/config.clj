;; This Source Code Form is subject to the terms of the Mozilla Public
;; License, v. 2.0. If a copy of the MPL was not distributed with this
;; file, You can obtain one at http://mozilla.org/MPL/2.0/

(ns hop-tutorial.api.config
  (:require [hop-tutorial.api.util.responses :as util.r]
            [integrant.core :as ig]))

(defn config-handler
  [config _req]
  (util.r/ok {:success? true
              :config config}))

(defmethod ig/init-key :hop-tutorial.api/config
  [_ config]
  ["/config"
   {:get {:summary "Return application's configuration"
          :swagger {:tags ["configuration"]}
          :handler (partial config-handler config)}}])
