package services;

import java.util.List;

import org.hibernate.Session;

 

import dao.Signature;
import util.HibernateUtil;

public class SignatureRepository {

	
	public static void addSignature(Signature sig){
		
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		session.save(sig);
		session.flush();
		session.getTransaction().commit(); 
	}
	public static Signature getSignature(Long idS){
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		Signature p = (Signature) session.load(Signature.class, idS);
		return p;
		
	} 
	
	public static  List<Signature> getAllSignatures(){
		
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		 
		
		return session.createQuery("from Signature").list(); 
		
		
	}
	
	
	 
}
