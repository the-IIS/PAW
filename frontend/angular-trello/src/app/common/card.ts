export class Ttable {
  id: number;
  tableName: string;
}

export class List {
  id: number;
  listName: string;
  ttable: Ttable;
}

export class Card {
  id: number;
  title: string;
  description: string;
  list: List;
}

