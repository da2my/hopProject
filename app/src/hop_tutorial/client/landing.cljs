;; This Source Code Form is subject to the terms of the Mozilla Public
;; License, v. 2.0. If a copy of the MPL was not distributed with this
;; file, You can obtain one at http://mozilla.org/MPL/2.0/

(ns hop-tutorial.client.landing
  (:require [hop-tutorial.client.view :as view]))

(defn- main []
  [:div.landing
   [:a {:href "https://www.gethop.dev/"
        :target "_blank"}
    [:img.landing__logo {:src "images/hop-logo.svg"}]]
   [:h1.landing__title "Your application is up and running on AWS!!;)!!LOL"]])

(defmethod view/view-display ::view
  [_]
  [main])
