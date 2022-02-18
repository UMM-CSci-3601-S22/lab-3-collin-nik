import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Todos, TodosStatus } from './todos';
import { TodosService } from './todos.service';

@Component({
  selector: 'app-todos-list',
  templateUrl: './todos-list.component.html',
  styleUrls: ['./todos-list.component.scss'],
  providers: []
})
export class TodosListComponent implements OnInit {

  public serverFilteredTodos: Todos[];
  public filteredTodos: Todos[];

  public todosId: string;
  public todosOwner: string;
  public todosStatus: boolean;
  public todosBody: string;
  public todosCategory: string;
  public viewType: 'card' | 'list' = 'card';

  constructor(private todosService: TodosService, private snackBar: MatSnackBar) { }

  getTodosFromServer() {
    this.todosService.getTodos({
      status: this.todosStatus
    }).subscribe(returnedTodos => {
      this.serverFilteredTodos = returnedTodos;
      this.updateFilter();
    }, err => {
      console.error('We couldn\'t get the list of todos; the server might be down');
      this.snackBar.open(
        'Problem contacting the server – try again',
        'Ok',
        { duration: 3000});
    });
  }

  public updateFilter() {
    this.filteredTodos = this.todosService.filterTodos(
      this.serverFilteredTodos, { owner: this.todosOwner, body: this.todosBody, category: this.todosCategory }
    );
  }

  ngOnInit(): void {
    this.getTodosFromServer();
  }

}
