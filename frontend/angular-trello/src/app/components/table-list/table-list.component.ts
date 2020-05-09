import { Component, OnInit } from '@angular/core';
import {TableService} from "../../services/table.service";
import {TablePayload} from "../../payloads/table-payload";

@Component({
  selector: 'app-table-list',
  templateUrl: './table-list.component.html',
  styleUrls: ['./table-list.component.css']
})
export class TableListComponent implements OnInit {

  tables: TablePayload[];

  constructor(private tableService: TableService) {}

  ngOnInit(): void {
    this.listTables();
  }

  listTables() {
    this.tableService.getTableList().subscribe(
      data => {
        this.tables = data;
      }
    );
  }
}
