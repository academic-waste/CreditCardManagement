import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { HttpClientInMemoryWebApiModule } from 'angular-in-memory-web-api';

import { AppComponent } from './app.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'; // <-- NgModel lives here



//导入组件库部分
import { NZ_ICONS } from 'ng-zorro-antd/icon';
import { IconDefinition } from '@ant-design/icons-angular';
import * as AllIcons from '@ant-design/icons-angular/icons';
//导入多个组件module
import { DemoNgZorroAntdModule } from './ng-zorro-antd.module';

// 导入 NzMessageModule
import { NzMessageModule } from 'ng-zorro-antd/message';
// 导入 NzTableModule，以便在整个模块中使用 ng-zorro-antd 表格组件和功能。
import { NzTableModule } from 'ng-zorro-antd/table';


//导入组件
import { DataDisplayComponent } from './data-display/data-display.component';
import { NavigateMenuComponent } from './navigate-menu/navigate-menu.component';
import { RegisterCardComponent } from './register-card/register-card.component';
import { DataAnalysisComponent } from './data-analysis/data-analysis.component';
import { DataService } from './services/in-transactions-data.service';
import { CancelCardComponent } from './cancel-card/cancel-card.component';

//nz-date-picker的部分locale 来自于 Angular 自身的国际化支持，需要引入相应的 Angular 语言包
// 导入英文语言包
import { NZ_I18N, en_US } from 'ng-zorro-antd/i18n';

// 注销模拟数据服务路径
import { CancelDataService } from './services/in-cacel-data.service';



const antDesignIcons = AllIcons as {
  [key: string]: IconDefinition;
};
const icons: IconDefinition[] = Object.keys(antDesignIcons).map(key => antDesignIcons[key])

@NgModule({
  declarations: [
    AppComponent,
    DataDisplayComponent,
    NavigateMenuComponent,
    RegisterCardComponent,
    DataAnalysisComponent,
    CancelCardComponent
  ],
  //这里放组件，然后其他文件从这个module.ts来引入？用了generate的话这里会自动添加
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    DemoNgZorroAntdModule,
    ReactiveFormsModule,
    HttpClientInMemoryWebApiModule.forRoot(DataService, { dataEncapsulation: false }),
    NzTableModule, // 确保已添加 NzTableModule 到 imports 数组中
    NzMessageModule, // 添加 NzMessageModule 到 imports 数组中
    HttpClientInMemoryWebApiModule.forRoot(CancelDataService) // 添加注销数据的这一行配置
  ],
  providers: [{ provide: NZ_I18N, useValue: en_US }],
  bootstrap: [AppComponent]
})
export class AppModule { }
