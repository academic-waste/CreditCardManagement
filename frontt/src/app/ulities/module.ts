//传给后端的数据
export interface TransactionsItem {
    gender: string;
    category: string;
    merchant: string;
    city: string;
    state: string;
    job:string;
    amt:number;
    [key: string]: string | number; // 添加索引签名
  }

  export interface TransactionsItemAll {
    gender: string;
    category: string;
    merchant: string;
    city: string;
    state: string;
    cityPopulation:number;
    amt:number
    transDateTransTime:string;
    transNum:number;
    first:string;
    last:string;
    job:string;
    dob:string
  }

export  interface ColumnItem {
    name: string;
    // sortOrder: NzTableSortOrder | null;
    // sortFn: NzTableSortFn<TransactionsItem> | null;
    // listOfFilter: NzTableFilterList;
    // filterFn: NzTableFilterFn<TransactionsItem> | null;
    // filterMultiple: boolean;
    // sortDirections: NzTableSortOrder[];
  }

export interface stateSum {
  state: string;
  total_transaction: number
}

export interface pieData {
  name:string;
  value:number
}