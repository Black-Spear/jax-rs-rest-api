package com.istic.rest;

public class Etudiant {
    private int id;
    private String nom;
    private double moyenne;

    public Etudiant() {
        // No-argument constructor
    }

    public Etudiant(int id, String nom, double moyenne) {
        this.id = id;
        this.nom = nom;
        this.moyenne = moyenne;
    }

    // Getter for id
    public int getId() {
        return id;
    }

    // Setter for id
    public void setId(int id) {
        this.id = id;
    }

    // Getter for nom
    public String getNom() {
        return nom;
    }

    // Setter for nom
    public void setNom(String nom) {
        this.nom = nom;
    }

    // Getter for moyenne
    public double getMoyenne() {
        return moyenne;
    }

    // Setter for moyenne
    public void setMoyenne(double moyenne) {
        this.moyenne = moyenne;
    }

    @Override
    public String toString() {
        return "Etudiant [id=" + id + ", nom=" + nom + ", moyenne=" + moyenne + "]";
    }
}
