(ns fraudulent-checker.events
  (:require
   [re-frame.core :as re-frame]
   [fraudulent-checker.db :as db]
   [day8.re-frame.http-fx]
   [ajax.core :as ajax]
   [md5.core :as md5]))

(re-frame/reg-event-db
 ::initialize-db
 (fn [_ _]
   db/default-db))


(re-frame/reg-event-db
 ::set-customer
 (fn [db [_ new-value]]
   (assoc-in db [:credentials :customer] new-value)))

(re-frame/reg-event-db
 ::set-user
 (fn [db [_ new-value]]
   (assoc-in db [:credentials :user] new-value)))

(re-frame/reg-event-db
 ::set-password
 (fn [db [_ new-value]]
   (assoc-in db [:credentials :password] new-value)))

(re-frame/reg-event-db
 ::login-succeed
 (fn [db [_ result]]
   (-> db
       (assoc-in [:result] result)
       (assoc-in [:credentials :status] :success))))

(re-frame/reg-event-db
 ::login-failed
 (fn [db _]
   (assoc-in db [:credentials :status] :error)))



(re-frame/reg-event-fx
 ::sign-in
 (fn [{:keys [db]} [_ {:keys [customer user password]}]]
   {:db (assoc-in db [:credentials :status] :success)
    :http-xhrio {:method :get
                 :uri "https://serwer.icpen.pl/api/v1/pl/Authorization/GetUser"
                 :timeout 8000
                 :headers {:customer customer
                           :login user
                           :password (md5.core/string->md5-hex password)}
                 :response-format (ajax/json-response-format {:keywords? true})
                 :on-success [::login-succeed]
                 :on-failure [::login-failed]}}))
