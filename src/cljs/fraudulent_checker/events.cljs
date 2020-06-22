(ns fraudulent-checker.events
  (:require
   [re-frame.core :as re-frame]
   [fraudulent-checker.db :as db]
   ))

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
 ::login
 (fn [db _]
   (assoc-in db [:credentials :status] :success)))