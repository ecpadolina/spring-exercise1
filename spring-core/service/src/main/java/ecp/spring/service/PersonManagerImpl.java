package ecp.spring.service;

import ecp.spring.dao.PersonDaoImpl;
import ecp.spring.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PersonManagerImpl implements PersonManager{

  private PersonDaoImpl personDaoImpl;
  private final Logger logger = LoggerFactory.getLogger(PersonManagerImpl.class);
  
  public void setPersonDaoImpl(PersonDaoImpl personDaoImpl){
      this.personDaoImpl = personDaoImpl;
  }
  
  public void addPerson(Person person){
    logger.info("Person Service addPerson() method");
    personDaoImpl.addPerson(person);
  }
  
  public void updatePerson(Person person){
    logger.info("Person Service updatePerson() method");
    personDaoImpl.updatePerson(person);
  }
  
  public void deletePerson(Person person){
    logger.info("Person Service deletePerson() method");
    personDaoImpl.deletePerson(person);
  }
  
  public Person getPerson(int id){
    logger.info("Person Service getPerson() method");
    return (Person)personDaoImpl.getPerson(id);
  }
  
  public List listPerson(int roleId, int order, String column){
    logger.info("Person Service listPerson() method");
    return personDaoImpl.listPerson(roleId, order,column);

  }

  /*public List listPersonWithRoles(int roleId){
    return personDaoImpl.listPersonWithRoles(roleId);
  }*/
  
}