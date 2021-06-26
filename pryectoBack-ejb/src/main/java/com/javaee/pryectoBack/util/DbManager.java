package com.javaee.pryectoBack.util;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.javaee.pryectoBack.model.EventoUsuarioId;
import com.javaee.pryectoBack.model.UsuarioContactoId;

public class DbManager {
	private static EntityManagerFactory emf;
	private static EntityManager em;

	public static EntityManager getEm() {
		return em;
	}
	
	public static void setEM(EntityManager m) {
		em=m;
	}
	public static void setEMF(EntityManagerFactory m) {
		emf=m;
	}
	
	public static void open() {
		try {
			if (emf == null) emf = Persistence.createEntityManagerFactory("primary");
			if (em == null) em = emf.createEntityManager();			
		}catch(Exception e){ 
			Logger logger = Logger.getLogger(DbManager.class.getName());       
			logger.log(Level.SEVERE, "Error al crear Entity Manager " + e.getMessage());
		}
	}
	
	public static boolean beginTransaction() {
		try {
			em.getTransaction().begin();
			return true;
		}catch(Exception e){
			Logger logger = Logger.getLogger(DbManager.class.getName());       
			logger.log(Level.SEVERE, "Error al comenzar transaccion " + e.getMessage());
			return false;
		}
	}
	
	public static boolean persist(Object o) {
		try {
			em.persist(o);
			return true;
		}catch(Exception e){
			Logger logger = Logger.getLogger(DbManager.class.getName());       
			logger.log(Level.SEVERE, "Error al persistir objeto " + e.getMessage());
			return false;
		}
	}

	public static <T> T find(Class<T> entityClass,String key) {
		T object = null;
		try {
			object = em.find(entityClass, key);
		}
		catch (Exception e) {
			Logger logger = Logger.getLogger(DbManager.class.getName());       
			logger.log(Level.SEVERE, "Error al buscar objeto " + e.getMessage());
			return null;
		}
		return object;
	}
	
	public static boolean remove(Object o) {
		try {
			em.remove(o);
			return true;
		}
		catch (Exception e) {
			Logger logger = Logger.getLogger(DbManager.class.getName());       
			logger.log(Level.SEVERE, "Error al borrar objeto " + e.getMessage());
			return false;
		}
	}
	
	public static boolean removeIfContains(Object o) {
		try {
			em.remove(em.contains(o) ? o : em.merge(o));
			return true;
		}
		catch (Exception e) {
			Logger logger = Logger.getLogger(DbManager.class.getName());       
			logger.log(Level.SEVERE, "Error al borrar objeto " + e.getMessage());
			return false;
		}
	}
	
	public static boolean commit() {
		try {
			em.getTransaction().commit();
			return true;
		}catch(Exception e){
			Logger logger = Logger.getLogger(DbManager.class.getName());       
			logger.log(Level.SEVERE, "Error al hacer commmit " + e.getMessage());
			return false;
		}
	}
	
	public static boolean close() {
		try {
			em.close();
			em = null;
			emf.close();
			emf = null;
			return true;
		}catch(Exception e){
			Logger logger = Logger.getLogger(DbManager.class.getName());       
			logger.log(Level.SEVERE, "Error al cerrar entitymanager " + e.getMessage());
			return false;
		}		
	}
	
	@SuppressWarnings("rawtypes")
	public static List createQuery(String query) {
		try 
		{
			List res = em.createQuery(query).getResultList();
			return res;
		}
		catch (Exception e)
		{
			Logger logger = Logger.getLogger(DbManager.class.getName());       
			logger.log(Level.SEVERE, "Error al obtener query " + e.getMessage());
			return null;
		}
	}

	@SuppressWarnings("rawtypes")
	public static List createQuery(String query, int offset, int size) {
		try 
		{
			List res = em.createQuery(query).setFirstResult(offset).setMaxResults(size).getResultList();
			return res;
		}
		catch (Exception e)
		{
			Logger logger = Logger.getLogger(DbManager.class.getName());       
			logger.log(Level.SEVERE, "Error al obtener query " + e.getMessage());
			return null;
		}
	}

	@SuppressWarnings("rawtypes")
	public static List createQuery(String query, String field, String parameter) {
		try 
		{
			List res = em.createQuery(query).setParameter(field, parameter).getResultList();
			return res;
		}
		catch (Exception e)
		{
			Logger logger = Logger.getLogger(DbManager.class.getName());       
			logger.log(Level.SEVERE, "Error al obtener query " + e.getMessage());
			return null;
		}
	}

	@SuppressWarnings("rawtypes")
	public static List createQuery(String query, String field, String parameter, int maxResult) {
		try 
		{
			List res = em.createQuery(query).setParameter(field, parameter).setMaxResults(maxResult).getResultList();
			return res;
		}
		catch (Exception e)
		{
			Logger logger = Logger.getLogger(DbManager.class.getName());       
			logger.log(Level.SEVERE, "Error al obtener query " + e.getMessage());
			return null;
		}
	}

	public static Long createQueryCount(String query) {
		return (Long) em.createQuery(query).getSingleResult();
	}

	public static boolean update(String query) {
		try {
			em.createQuery(query).executeUpdate();
			return true;
		}
		catch (Exception e) {
			Logger logger = Logger.getLogger(DbManager.class.getName());       
			logger.log(Level.SEVERE, "Error al update tabla " + e.getMessage());
			return false;
		}
	}
	
	public static boolean merge(Object o) {
		try {
			em.merge(o);
			return true;
		}catch(Exception e){
			Logger logger = Logger.getLogger(DbManager.class.getName());       
			logger.log(Level.SEVERE, "Error al merge objeto " + e.getMessage());
			return false;
		}
	}

	public static <T> T  find(Class<T> entityClass, int entityId) {
		T object = null;
		try {
			object = em.find(entityClass, entityId);
		}
		catch (Exception e) {
			Logger logger = Logger.getLogger(DbManager.class.getName());       
			logger.log(Level.SEVERE, "Error al buscar objeto " + e.getMessage());
			return null;
		}
		return object;
	}

	public static  <T> T  find(Class<T> entityClass, String idPersona, int idEvento) {
		T object = null;
		try {
			object = em.find(entityClass, new EventoUsuarioId(idPersona, idEvento));
		}
		catch (Exception e) {
			Logger logger = Logger.getLogger(DbManager.class.getName());       
			logger.log(Level.SEVERE, "Error al buscar objeto " + e.getMessage());
			return null;
		}
		return object;
	}

	public static  <T> T  find(Class<T> entityClass, String idPersona, String idContacto) {
		T object = null;
		try {
			object = em.find(entityClass, new UsuarioContactoId(idPersona, idContacto));
		}
		catch (Exception e) {
			Logger logger = Logger.getLogger(DbManager.class.getName());       
			logger.log(Level.SEVERE, "Error al buscar objeto " + e.getMessage());
			return null;
		}
		return object;
	}
}