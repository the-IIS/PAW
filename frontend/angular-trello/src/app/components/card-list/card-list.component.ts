import { Component, OnInit } from '@angular/core';
import { CardService } from 'src/app/services/card.service';
import {Card, List, Ttable} from 'src/app/common/card';

@Component({
  selector: 'app-card-list',
  templateUrl: './card-list.component.html',
  styleUrls: ['./card-list.component.css']
})
export class CardListComponent implements OnInit {

  cards: Card[];
  lists: List[];
  tables: Ttable[];

  constructor(private cardService: CardService) {}

  ngOnInit(): void {
    this.listCards();
    this.listLists();
    this.listTables();
  }

  listCards() {
    this.cardService.getCardList().subscribe(
      data => {
        this.cards = data;
      }
    );
  }

  listLists() {
    this.cardService.getListList().subscribe(
      data => {
        this.lists = data;
      }
    );
  }

  listTables() {
    this.cardService.getTableList().subscribe(
      data => {
        this.tables = data;
      }
    );
  }
}
