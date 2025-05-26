import { Category } from './category.model';

describe('Category', () => {
  it('should create an instance', () => {
    expect(new Category(undefined, this.selectedCategory.id)).toBeTruthy();
  });
});
