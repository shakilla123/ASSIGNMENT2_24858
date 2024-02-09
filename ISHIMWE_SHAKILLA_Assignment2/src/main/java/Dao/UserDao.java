package Dao;

import java.util.Collections;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;




public class UserDao {
	
	public void saveUser(Model.User user) {
        Transaction transaction = null;
        try (Session session =Util.Hibernateutil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.save(user);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

	
	 public boolean validateUser(String userName, String password) {
	        Transaction transaction = null;
	        Model.User user = null;
	        try (Session session = Util.Hibernateutil.getSessionFactory().openSession()) {
	            // start a transaction
	            transaction = session.beginTransaction();
	            // get a user object
	            user = (Model.User) session.createQuery("FROM User U where U.username = :userName")
	                    .setParameter("userName", userName).uniqueResult();
	            if (user != null && user.getPassword().equals(password)) {
	                return true;
	            }
	            // commit transaction
	            transaction.commit();
	        } catch (Exception e) {
	            if (transaction != null) {
	                transaction.rollback();
	            }
	            e.printStackTrace();
	        }
	        return false;
	    }
	 public void updateUser(Model.User user) {
		    Transaction transaction = null;
		    try (Session session = Util.Hibernateutil.getSessionFactory().openSession()) {
		        // start a transaction
		        transaction = session.beginTransaction();
		        // update the user object
		        session.update(user);
		        // commit transaction
		        transaction.commit();
		    } catch (Exception e) {
		        if (transaction != null) {
		            transaction.rollback();
		        }
		        e.printStackTrace();
		    }
		}
	 public void deleteUser(Model.User user) {
		    Transaction transaction = null;
		    try (Session session = Util.Hibernateutil.getSessionFactory().openSession()) {
		        // start a transaction
		        transaction = session.beginTransaction();
		        // delete the user object
		        session.delete(user);
		        // commit transaction
		        transaction.commit();
		    } catch (Exception e) {
		        if (transaction != null) {
		            transaction.rollback();
		        }
		        e.printStackTrace();
		    }
		}
	 public List<Model.User> getAllUsers() {
		    try (Session session = Util.Hibernateutil.getSessionFactory().openSession()) {
		        // retrieve all users
		        return session.createQuery("FROM User", Model.User.class).list();
		    } catch (Exception e) {
		        e.printStackTrace();
		        return Collections.emptyList();
		    }
		}


}
