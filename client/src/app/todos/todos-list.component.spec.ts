import { TestBed, waitForAsync } from '@angular/core/testing';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatOptionModule } from '@angular/material/core';
import { MatDividerModule } from '@angular/material/divider';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatListModule } from '@angular/material/list';
import { MatRadioModule } from '@angular/material/radio';
import { MatSelectModule } from '@angular/material/select';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatTooltipModule } from '@angular/material/tooltip';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterTestingModule } from '@angular/router/testing';
import { Observable } from 'rxjs';
import { MockTodosService } from '../../testing/todos.service.mock';
import { Todos } from './todos';
import { TodosCardComponent } from './todos-card.component';
import { TodosListComponent } from './todos-list.component';
import { TodosService } from './todos.service';

const COMMON_IMPORTS: any[] = [
  FormsModule,
  MatCardModule,
  MatFormFieldModule,
  MatSelectModule,
  MatOptionModule,
  MatButtonModule,
  MatInputModule,
  MatExpansionModule,
  MatTooltipModule,
  MatListModule,
  MatDividerModule,
  MatRadioModule,
  MatSnackBarModule,
  BrowserAnimationsModule,
  RouterTestingModule,
];

let todoList: TodosListComponent;

async function constructTodosList() {
  await TestBed.compileComponents();
  const fixture = TestBed.createComponent(TodosListComponent);
  todoList = fixture.componentInstance;
  fixture.detectChanges();
}

describe('TodosListComponent', () => {

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [COMMON_IMPORTS],
      declarations: [ TodosListComponent, TodosCardComponent ],
      providers: [{ provide: TodosService, useValue: new MockTodosService()}]
    });
  });

  beforeEach(waitForAsync(constructTodosList));

  it('contains all the todos', () => {
    expect(todoList.serverFilteredTodos.length).toBe(5);
  });

  it('contains a todo with owner "Barry"', () => {
    expect(todoList.serverFilteredTodos.some((todo: Todos) => todo.owner === 'Barry')).toBe(true);
  });

  it('doesn\'t contains a todo with owner "Workman"', () =>{
    expect(todoList.serverFilteredTodos.some((todo: Todos) => todo.owner === 'Workman')).toBe(false);
  });

  it('contains a complete todo items', () => {
    expect(todoList.serverFilteredTodos.some((todo: Todos) => todo.status)).toBe(true);
  });
});
