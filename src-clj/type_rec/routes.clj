;; Copyright (c) 2013, Robert welin
(ns type-rec.routes
  (:use compojure.core
        [hiccup.middleware :only (wrap-base-url)])
  (:require [compojure.route :as route]
            [compojure.handler :as handler]
            [compojure.response :as response]))

(defroutes main-routes
  (GET "/" [])
  (route/files "/" {:root "resources/public"})
  (route/not-found "Page not found"))

(def app
  (-> (handler/site main-routes)
      (wrap-base-url)))
