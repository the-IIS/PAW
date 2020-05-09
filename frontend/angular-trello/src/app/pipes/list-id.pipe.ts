import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'listId'
})
export class ListIdPipe implements PipeTransform {

  transform(values: any, ...args: unknown[]): unknown {
    return values.filter(value => value.list.id === args[0]);
  }

}
