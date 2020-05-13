package fr.uvsq.pglp_9_9.Dao;



public abstract class  AbstractFactoryDao<T> {

	public abstract T create(T object);
    
    public abstract T find(String id);
   
    public abstract T update(T object);
   
    public abstract void delete(T object);
 
  
}
