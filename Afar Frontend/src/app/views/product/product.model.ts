export class Product {

    constructor(
      public id?: String,
      private name?: String,
      private price?: number,
      private description?: String,
      private discountPriceperPoints?: number,
      private discountPoints?: number
    ) { }

}
