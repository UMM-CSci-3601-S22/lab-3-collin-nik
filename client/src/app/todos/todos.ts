export interface Todos {
  _id: string;
  owner: string;
  status: boolean;
  body: string;
  category: string;
}

export type TodosStatus = false | true;
export type TodosSort = 'Owner' | 'Body' | 'Category' | 'Status';
export type TodosLimit = 2 | 10 | 20 | 50 | 100;
