import { Component, OnInit } from '@angular/core';
import { Categorie } from '../model/categorie.model';
import { Produit } from '../model/produit.model';
import { ProduitService } from '../services/produit.service';

@Component({
  selector: 'app-recherche-par-categorie',
  templateUrl: './recherche-par-categorie.component.html',
  styleUrl: './recherche-par-categorie.component.css'
})
export class RechercheParCategorieComponent implements OnInit {
  IdCategorie: number = 1;
  categories!: Categorie[];
  produits!: Produit[ ];

  constructor(private produitService: ProduitService) {}

  ngOnInit(): void {
    this.produitService.listerCategories().subscribe((cats) => {
      this.categories = cats._embedded.categories;
      console.log(cats);
    });

    this.produitService
      .rechercherProduitsParCategorie(1)
      .subscribe((prods) => {
        this.produits = prods;
    });
  }

  onChange() {
    this.produitService
      .rechercherProduitsParCategorie(this.IdCategorie)
      .subscribe((prods) => {
        this.produits = prods;
    });
  }

  scrollToTop() {
    window.scrollTo({top: 0, behavior: 'smooth'}); 
  }

}
