(ns fraudulent-checker.views
  (:require
   [re-frame.core :as re-frame]
   [re-com.core :as re-com]
   [fraudulent-checker.subs :as subs]
   [fraudulent-checker.events :as events]
   ))

(defn title []
  (let [name (re-frame/subscribe [::subs/name])]
    [re-com/title
     :label (str "Helloaaa a from " @name)
     :level :level1]))


(defn credentials []
  (let [user (re-frame/subscribe [::subs/user])
        customer (re-frame/subscribe [::subs/customer])
        password (re-frame/subscribe [::subs/password])
        status (re-frame/subscribe [::subs/status])]
    
    [re-com/v-box
     :height "200px"
     :gap "10px"
     :children [[re-com/input-text
                 :model @customer
                 :status @status
                 :change-on-blur? false
                 :on-change #(re-frame/dispatch [::events/set-customer %])
                 :placeholder "Klient"]
                [re-com/input-text
                 :model @user
                 :status @status
                 :change-on-blur? false
                 :on-change #(re-frame/dispatch [::events/set-user %])
                 :placeholder "Użytkownik"]
                [re-com/input-text
                 :model @password
                 :status @status
                 :change-on-blur? false
                 :input-type :password
                 :on-change #(re-frame/dispatch [::events/set-password %])
                 :placeholder "Hasło"]
                [re-com/button
                 :on-click #(re-frame/dispatch [::events/login])
                 :label "Zaloguj"]]]))


(defn main-panel []
  [re-com/v-box
   :height "100%"
   :children [[title]
              [credentials]
              ]])
