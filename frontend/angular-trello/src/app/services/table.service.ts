import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {TablePayload} from '../payloads/table-payload';
import {Observable} from 'rxjs';
import {CardPayload} from '../payloads/card-payload';
import {CardListPayload} from '../payloads/card-list-payload';
import {FilePayload} from '../payloads/file-payload';

@Injectable({
  providedIn: 'root'
})
export class TableService {
  private jsonPost: { table_id: number; listName: string };
  private jsonCardPost: { cardName: string, description: string, cardListId: number };

  constructor(private httpClient: HttpClient) { }

  getTableList(): Observable<TablePayload[]> {
    return this.httpClient.get<TablePayload[]>('http://localhost:8080/api/table-list/all');
  }

  getTable(permaLink: number): Observable<TablePayload> {
    return this.httpClient.get<TablePayload>('http://localhost:8080/api/table-list/get/' + permaLink);
  }

  getCardLists(tableId: number): Observable<CardListPayload[]> {
    return this.httpClient.get<CardListPayload[]>('http://localhost:8080/api/card-list/get/table/' + tableId);
  }

  getCards(tableId: number): Observable<CardPayload[]> {
    return this.httpClient.get<CardPayload[]>('http://localhost:8080/api/card/get/table/' + tableId);
  }

  deleteTable(tableId: number): Observable<{}> {
    return this.httpClient.delete<any>('http://localhost:8080/api/table-list/delete/' + tableId);
  }

  addTable(tableName: string): Observable<{}> {
    return this.httpClient.post<any>('http://localhost:8080/api/table-list/add/' + tableName, null);
  }

  updateTable(tableId: number, tableName: string): Observable<{}> {
    return this.httpClient.put<any>('http://localhost:8080/api/table-list/' + tableId + '/' + tableName, null);
  }

  addList(tableId: number, name: string): Observable<{}> {
    this.jsonPost = {
      listName: name,
      table_id: tableId
    };
    return this.httpClient.post<any>('http://localhost:8080/api/card-list/add', this.jsonPost);
  }

  editCardList(cardListId: number, newListName: string, newTableId: number): Observable<{}> {
    this.jsonPost = {
      listName: newListName,
      table_id: newTableId
    };
    return this.httpClient.put<any>('http://localhost:8080/api/card-list/update/' + cardListId, this.jsonPost);
  }

  archiveCardList(tableId: number): Observable<CardListPayload[]> {
    return this.httpClient.get<any>('http://localhost:8080/api/card-list/' + tableId + '/archive/');
  }

  addCard(cardList: number, name: string, desc: string): Observable<{}> {
    this.jsonCardPost = {
      cardName: name,
      description: desc,
      cardListId: cardList
    };
    return this.httpClient.post<any>('http://localhost:8080/api/card/add', this.jsonCardPost);
  }

  editCard(cardList: number, name: string, desc: string): Observable<{}> {
    this.jsonCardPost = {
      cardName: name,
      description: desc,
      cardListId: cardList
    };
    // TODO repair update
    return this.httpClient.put<any>('http://localhost:8080/api/card/update/' + this.jsonCardPost.cardListId, this.jsonCardPost);
  }

  getFiles(cardId: number): Observable<FilePayload[]> {
    return this.httpClient.get<FilePayload[]>('http://localhost:8080/api/file/?cardId=' + cardId);
  }

}
