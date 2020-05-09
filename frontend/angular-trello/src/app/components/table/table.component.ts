import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {TableService} from '../../services/table.service';
import {TablePayload} from '../../payloads/table-payload';
import {CardPayload} from '../../payloads/card-payload';
import {CardListPayload} from '../../payloads/card-list-payload';
import {Observable} from "rxjs";
import {map} from "rxjs/operators";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent implements OnInit {

  table: TablePayload;
  cardLists: CardListPayload[];
  cards: CardPayload[];
  permaLink: number;

  constructor(private router: ActivatedRoute, private tableService: TableService, private httpClient: HttpClient) { }

  ngOnInit(): void {
    this.router.params.subscribe(params => {
      this.permaLink = params.id;
    });
    this.getTable();
  }



  getTable() {
    this.tableService.getTable(this.permaLink).subscribe((data: TablePayload) => {
      this.table = data;
      this.getCardLists(this.table.id);
      this.getCards(this.table.id);
    }, (error => {
      console.log('Błąd');
    }));
  }

  getCardLists(tableId: number) {
    this.tableService.getCardLists(tableId).subscribe((data: CardListPayload[]) => {
      this.cardLists = data;
    }, (error => {
      console.log('Błąd');
    }));
  }

  getCards(tableId: number) {
    this.tableService.getCards(tableId).subscribe((data: CardPayload[]) => {
      this.cards = data;
    }, (error => {
      console.log('Błąd');
    }));
  }

}
