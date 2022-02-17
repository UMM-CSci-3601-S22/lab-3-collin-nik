import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Todos } from './todos';
import { TodosStatus} from './todos';

@Injectable()
export class TodosService {
  readonly todosUrl: string = environment.apiUrl + 'todos';

  constructor(private httpClient: HttpClient) { }

  getTodos(filters?: { status?: TodosStatus}): Observable<Todos[]> {
    let httpParams: HttpParams = new HttpParams();
    if (filters.status) {
      httpParams = httpParams.set('status', filters.status);
    }
    return this.httpClient.get<Todos[]>(this.todosUrl, {
      params: httpParams,
    });
  }

  getTodosById(id: string): Observable<Todos> {
    return this.httpClient.get<Todos>(this.todosUrl + '/' + id);
  }

  filterTodos(todos: Todos[], filters: {owner?: string; body?: string; category?: string }): Todos[] {
    let filteredTodos = todos;

    if(filters.owner) {
      filters.owner = filters.owner.toLowerCase();
      filteredTodos = filteredTodos.filter(todo => todo.owner.toLowerCase().indexOf(filters.owner) !== -1);
    }

    if(filters.body) {
      filters.body = filters.body.toLowerCase();
      filteredTodos = filteredTodos.filter(todo => todo.body.toLowerCase().indexOf(filters.body) !== -1);
    }

    if(filters.category) {
      filters.category = filters.category.toLowerCase();
      filteredTodos = filteredTodos.filter(todo => todo.category.toLowerCase().indexOf(filters.category) !== -1);
    }

    return filteredTodos;
  }
}
