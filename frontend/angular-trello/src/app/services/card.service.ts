import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Card } from '../common/card';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class CardService {

  private baseUrl = 'http://localhost:8080/api/cards';

  constructor(private httpClient: HttpClient) { }

  getCardList(): Observable<Card[]> {
    return this.httpClient.get<GetResponse>(this.baseUrl).pipe(
      map(response => response._embedded.cards)
    );
  }
}

interface GetResponse {
  _embedded: {
    cards: Card[];
  }
}
