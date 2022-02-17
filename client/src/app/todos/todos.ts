export interface Todos {
  _id: string;
  owner: string;
  status: boolean;
  body: string;
  category: string;
}

export type TodosStatus = false | true;
