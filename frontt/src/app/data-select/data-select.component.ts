import { Component, OnInit } from '@angular/core';
import { UntypedFormBuilder, UntypedFormControl, UntypedFormGroup } from '@angular/forms';

@Component({
  selector: 'app-data-select',
  templateUrl: './data-select.component.html',
  styleUrls: ['./data-select.component.css']
})
export class DataSelectComponent implements OnInit{
  validateForm!: UntypedFormGroup;
  controlArray: Array<{ index: number; show: boolean }> = [];
  isCollapse = true;

  resetForm(): void {
    this.validateForm.reset();
  }

  constructor(private fb: UntypedFormBuilder) {}

  ngOnInit(): void {
    this.validateForm = this.fb.group({});
    for (let i = 0; i < 10; i++) {
      this.controlArray.push({ index: i, show: i < 6 });
      this.validateForm.addControl(`field${i}`, new UntypedFormControl());
    }
  }
}
