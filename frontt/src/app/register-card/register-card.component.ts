import { Component, OnInit } from '@angular/core';
import { UntypedFormBuilder, UntypedFormControl, UntypedFormGroup, Validators } from '@angular/forms';

import { NzFormTooltipIcon } from 'ng-zorro-antd/form';
import { NzDatePickerModule } from 'ng-zorro-antd/date-picker';
import { getISOWeek } from 'date-fns';

// 创建表单并使用HttpClient发送表单数据到虚拟后端接口。
// import { FormBuilder, FormGroup } from '@angular/forms';
// import { HttpClient } from '@angular/common/http';
import { NzMessageService } from 'ng-zorro-antd/message';

// interface RegisterFormData {
//   firstName: string;
//   lastName: string;
//   gender: string;
//   job: string;
//   dob: Date;
// }

@Component({
  selector: 'app-register-card',
  templateUrl: './register-card.component.html',
  styleUrls: ['./register-card.component.css']
})
export class RegisterCardComponent implements OnInit {
  validateForm!: UntypedFormGroup;
  // dob: Date = new Date();

  // 提交表单数据
  submitForm(): void {
    if (this.validateForm.valid) {
      const formData = this.validateForm.value;

      // 模拟后端请求，使用 setTimeout 模拟异步请求
      setTimeout(() => {
        // 输出表单提交的数据
        console.log('Form Data:', formData);
        // 查看dob数据类型
        console.log(typeof formData['dob']);
        // console.log(typeof formData.controls['dob'].value);
        // 模拟成功响应
        this.message.success('Successfully registered！');
      }, 1000);

      // const formData = this.validateForm.value;
      // this.http.post('/api/users', formData).subscribe(
      //   response => {
      //     console.log('Data sent successfully:', response);
      //   },
      //   error => {
      //     console.error('Error sending data:', error);
      //   }
      // );

    } else {
      Object.values(this.validateForm.controls).forEach(control => {
        if (control.invalid) {
          control.markAsDirty();
          control.updateValueAndValidity({ onlySelf: true });
        }
      });
    }
  }

  // 选择性别
  genderChange(value: string): void {
    this.validateForm.get('note')!.setValue(value === 'male' ? 'Hi, man!' : 'Hi, lady!');
  }

  constructor(private fb: UntypedFormBuilder, private message: NzMessageService) {
    this.validateForm = this.fb.group({
      firstName: '',
      lastName: '',
      gender: '',
      job: '',
      dob: null
    });
  }

  ngOnInit(): void {
    //表单验证
    this.validateForm = this.fb.group({
      firstName: ['', [Validators.required, Validators.maxLength(20), Validators.pattern('[^0-9]*')]],
      lastName: ['', [Validators.required, Validators.maxLength(20), Validators.pattern('[^0-9]*')]],
      gender: ['', [Validators.required]],
      job: ['', [Validators.required, Validators.minLength(4), Validators.pattern('[^0-9]*')]],
      dob: [null, [Validators.required]]
    });
  }
}
