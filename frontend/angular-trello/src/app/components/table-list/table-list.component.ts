import { Component, OnInit } from '@angular/core';
import { CardService } from 'src/app/services/card.service';
import {Ttable} from '../../common/card';

@Component({
  selector: 'app-table-list',
  templateUrl: './table-list.component.html',
  styleUrls: ['./table-list.component.css']
})
export class TableListComponent implements OnInit {

  tables: Ttable[];

  constructor(private cardService: CardService) {}

  ngOnInit(): void {
    this.listTables();
  }

  listTables() {
    this.cardService.getTableList().subscribe(
      data => {
        this.tables = data;
      }
    );
  }
}
