import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Card, List} from '../common/card';
import { Ttable } from '../common/card';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class CardService {

  private baseCardUrl = 'http://localhost:8080/api/card/all';

  private baseListUrl = 'http://localhost:8080/api/card-list/all';

  private baseTableUrl = 'http://localhost:8080/api/table-list/all';

  constructor(private httpClient: HttpClient) { }

  getCardList(): Observable<Card[]> {
    return this.httpClient.get<Card[]>(this.baseCardUrl).pipe(
      map(response => response)
    );
  }

  getListList(): Observable<List[]> {
    return this.httpClient.get<List[]>(this.baseListUrl).pipe(
      map(response => response)
    );
  }

  getTableList(): Observable<Ttable[]> {
    return this.httpClient.get<Ttable[]>(this.baseTableUrl).pipe(
      map(response => response)
    );
  }
}
