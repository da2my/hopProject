;; This Source Code Form is subject to the terms of the Mozilla Public
;; License, v. 2.0. If a copy of the MPL was not distributed with this
;; file, You can obtain one at http://mozilla.org/MPL/2.0/

(ns ^:figwheel-hooks hop-tutorial.client
  (:require [day8.re-frame.http-fx]
            [hop-tutorial.client.config :as client.config]
            [hop-tutorial.client.routes :as routes]
            [hop-tutorial.client.view :as view]
            [re-frame.core :as rf]
            [reagent.dom :as rd]))

(rf/reg-event-fx
 ::on-config-loaded
 (fn [_ _]
   {:fx []}))

(rf/reg-event-fx
 ::load-app
 (fn [_ _]
   {:db {}
    :dispatch [::client.config/load-config [::on-config-loaded]]}))

(defn app []
  [:div.app-container
   {:id "app-container"}
   [view/main]])

(defn main []
  [app])

;; Make log level logs no-ops for production environment.
(rf/set-loggers! {:log (fn [& _])})

(defn dev-setup []
  (when goog.DEBUG
    ;; Reenable log level logs no-ops for dev environment.
    (rf/set-loggers! {:log js/console.log})
    (enable-console-print!)
    (println "Dev mode")))

(defn mount-root []
  (rf/clear-subscription-cache!)
  (rd/render [main] (.getElementById js/document "app")))

(defn ^:after-load re-render []
  (mount-root))

(defn ^:export init []
  (dev-setup)
  (rf/dispatch-sync [::load-app])
  (routes/init-routes!)
  (mount-root))
