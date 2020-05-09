import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {TableService} from '../../services/table.service';
import {TablePayload} from '../../payloads/table-payload';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent implements OnInit {

  table: TablePayload;
  permaLink: number;

  constructor(private router: ActivatedRoute, private tableService: TableService) { }

  ngOnInit(): void {
    this.router.params.subscribe(params => {
      this.permaLink = params.id;
    });
    this.tableService.getTable(this.permaLink).subscribe((data: TablePayload) => {
      this.table = data;
    }, (error => {
      console.log('Błąd');
    }));
  }

}
