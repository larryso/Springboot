//package com.larry.test;
//
//import ma.glasnost.orika.MapperFacade;
//import ma.glasnost.orika.MapperFactory;
//import ma.glasnost.orika.impl.DefaultMapperFactory;
//import org.junit.Assert;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//
//public class BeanMapperTest {
//    private static MapperFactory mapperFactory;
//    @BeforeAll
//    public static void initMapperFactory(){
//        mapperFactory = new DefaultMapperFactory.Builder().build();
//    }
//    @Test
//    public void mapWithDiffField(){
////        mapperFactory.classMap(Personne.class, Person.class)
////                .field("nom", "name")
////                .field("surnom", "nickname")
////                .register();
////        MapperFacade mapperFacade = mapperFactory.getMapperFacade();
////        Personne personne = new Personne("Larry", "Song");
////        Person person = mapperFacade.map(personne, Person.class);
//////        Assert.assertEquals(personne.getNom()+"5", person.getName());
//////        Assert.assertEquals(personne.getSurnom(), person.getNickname());
//    }
//}
//
//class Person{
//    private String name;
//    private String nickname;
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getNickname() {
//        return nickname;
//    }
//
//    public void setNickname(String nickname) {
//        this.nickname = nickname;
//    }
//}
//class Personne{
//    private String nom;
//    private String surnom;
//
//    public Personne(String nom, String surnom){
//        this.nom = nom;
//        this.surnom = surnom;
//    }
//
//
//    public String getNom() {
//        return nom;
//    }
//
//    public void setNom(String nom) {
//        this.nom = nom;
//    }
//
//    public String getSurnom() {
//        return surnom;
//    }
//
//    public void setSurnom(String surnom) {
//        this.surnom = surnom;
//    }
//}
