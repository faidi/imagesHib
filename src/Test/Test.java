package Test;

 
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dao.Image;
import dao.Signature;
import services.SignatureRepository; 
import util.HibernateUtil;

public class Test {

	public static void main(String[] args) {
	/*	// TODO Auto-generated method stub
		SignatureRepository sigManager = new SignatureRepository(); 
		Signature sig=new Signature();
		Integer[] tab={1,2,4,5};
  //Signature sig2=new Signature(tab,tab,tab,"test","test","test","test","test","test");
 
 
 //sigManager.addSignature(sig2);
  
 
	 sig=sigManager.getSignature(new Long(1));
	 for(int i=0;i<tab.length;i++)
		System.out.print(sig.getTabBY()[i] ); 
	}*/
		  Session sf = HibernateUtil.getSession();
	        //Session session = sf.openSession();
	        sf.beginTransaction();
		
		
		List<Image> employees = sf.createQuery("from Image").list();
        for (Image employee1 : employees) {
            System.out.println(employee1.getName()  + " , "
                    + employee1.getSignature().toString()   );
                    
        }
 
        sf.getTransaction().commit();
        sf.close();
	}
}
