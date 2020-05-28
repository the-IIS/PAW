import {CardPayload} from './card-payload';

export class FilePayload {

  id: number;
  name: string;
  mimetype: string;
  pic: string;
  list: CardPayload;
}
