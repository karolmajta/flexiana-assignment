(ns flexiana-assignment.core
  (:require
   [ring.middleware.params :refer [wrap-params]]
   [ring.middleware.cors :refer [wrap-cors]]
   [compojure.core :refer [defroutes GET ANY]]
   [compojure.route :refer [not-found]]
   [flexiana-assignment.resources :refer [scramble?]]))

(defroutes api-routes
  (GET "/:source/:target" [source target] (scramble? source target))
  (ANY "/*" [] (not-found "Not found.")))

(def api-handler
  (-> api-routes
      wrap-params
      (wrap-cors :access-control-allow-origin [#".*"] :access-control-allow-methods [:get])))