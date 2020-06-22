(ns fraudulent-checker.core
  (:require
   [reagent.core :as reagent]
   [reagent.dom :as rdom]
   [re-frame.core :as re-frame]
   [fraudulent-checker.events :as events]
   [fraudulent-checker.views :as views]
   [fraudulent-checker.config :as config]
   ))


(defn dev-setup []
  (when config/debug?
    (println "dev mode")))

(defn ^:dev/after-load mount-root []
  (re-frame/clear-subscription-cache!)
  (let [root-el (.getElementById js/document "app")]
    (rdom/unmount-component-at-node root-el)
    (rdom/render [views/main-panel] root-el)))

(defn init []
  (re-frame/dispatch-sync [::events/initialize-db])
  (dev-setup)
  (mount-root))
