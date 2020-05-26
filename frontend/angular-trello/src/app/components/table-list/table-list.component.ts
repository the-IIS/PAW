import {Component, NgModule, OnInit} from '@angular/core';
import {TableService} from '../../services/table.service';
import {TablePayload} from '../../payloads/table-payload';
import {FormControl} from '@angular/forms';
import {HttpClient, HttpParams} from '@angular/common/http';
import {AuthService} from '../../services/auth.service';
import {Router} from '@angular/router';



@Component({
  selector: 'app-table-list',
  templateUrl: './table-list.component.html',
  styleUrls: ['./table-list.component.css']
})
export class TableListComponent implements OnInit {

  name = new FormControl('');

  tables: TablePayload[];
  private postId: any;

  constructor(private tableService: TableService, private http: HttpClient, private router: Router) {}

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

  addTable() {
    this.http.post<any>('http://localhost:8080/api/table-list', { 	tableName : this.name.value }).subscribe(data => {
      this.postId = data.id;
    });
    // this.router.navigateByUrl('/tableList').then(r => true); // TODO add refresh page
  }

  deleteTable(tableId) {
    this.tableService.deleteTable(tableId).subscribe(data => {
      this.router.routeReuseStrategy.shouldReuseRoute = () => false;
      this.router.onSameUrlNavigation = 'reload';
      this.router.navigateByUrl('/tableList').then(r => true);
    });
  }

}
