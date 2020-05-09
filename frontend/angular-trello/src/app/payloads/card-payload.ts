import {CardListPayload} from './card-list-payload';

export class CardPayload {
  id: number;
  title: string;
  description: string;
  list: CardListPayload;
}
