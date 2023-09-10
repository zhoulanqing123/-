<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="当天时间" prop="createDate">
        <el-date-picker clearable
          v-model="queryParams.createDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择当天时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="统计月份" prop="queryMonth">
        <el-date-picker clearable
          v-model="queryParams.queryMonth"
          type="month"
          value-format="yyyy-MM"
          placeholder="请选择统计月份">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="当日星期" prop="day">
        <el-input
          v-model="queryParams.day"
          placeholder="请输入当日星期"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="当日营业额" prop="getAllMoney">
        <el-input
          v-model="queryParams.getAllMoney"
          placeholder="请输入当日营业额"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['shop:baozi:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['shop:baozi:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['shop:baozi:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['shop:baozi:export']"
        >导出</el-button>
      </el-col>
      <el-col :span="3">
        <el-button
          type=""
          plain
          icon="el-icon-chart"
          size="mini"
          @click="openFenXi = true"
        >当月数据报表</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>
    <el-div :gutter="40" ref="tongji" :model="tongji" :data="getTongJi"  >
      <el-col :span="5"  >
        <el-div
          class="padding-content" style="color: yellowgreen;font-size: 16px;font-weight:bold"
        >当前月份:{{tongji.curMonth}}</el-div>
      </el-col>

      <el-col :span="5">
        <el-div
          class="padding-content" style="color: yellowgreen;font-size: 16px;font-weight:bold"
        >本月营业额:{{tongji.curGetAllMoney}}</el-div>
      </el-col>

      <el-col :span="5">
        <el-div
          size="large"
          class="padding-content" style="color: yellowgreen;font-size: 16px;font-weight:bold"
        >本月进货金额:{{tongji.curPutAllMoney}}</el-div>
      </el-col>

      <el-col :span="5">
        <el-div
          type="warning"
          size="large"
          class="padding-content" style="color: yellowgreen;font-size: 16px;font-weight:bold"
        >本月净利润:{{tongji.curActualMoney}}</el-div>
      </el-col>
    </el-div>

    <el-table v-loading="loading" :data="baoziList" show-summary :summary-method="getSummaries" :cell-style="columnStyle" @selection-change="handleSelectionChange" >
      <el-table-column type="selection" width="55" align="center" />
<!--      <el-table-column label="id" align="center" prop="id" />-->
      <el-table-column label="当天时间" align="center" prop="createDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column @click='innerEdit(scope.$index, scope.row,baoziList)' label="当日星期" align="center" prop="day" >
      </el-table-column>
      <el-table-column label="店铺名称" align="center" prop="name"/>
      <el-table-column label="支付宝收款金额" align="center" prop="getPayMoney">
        <template slot-scope="scope">
          <template v-if="scope.row.edit">
            <el-input class="edit-input" size="small" v-model="scope.row.getPayMoney"></el-input>
          </template>
          <span @click='innerEdit(scope.$index, scope.row,baoziList)' v-else>{{ scope.row.getPayMoney }}</span>
        </template>
      </el-table-column>
      <el-table-column label="微信收款金额" align="center" prop="getWechatMoney">
        <template slot-scope="scope">
          <template v-if="scope.row.edit">
            <el-input class="edit-input" size="small" v-model="scope.row.getWechatMoney"></el-input>
          </template>
          <span @click='innerEdit(scope.$index, scope.row,baoziList)' v-else>{{ scope.row.getWechatMoney }}</span>
        </template>
      </el-table-column>
      <el-table-column label="现金收款金额" align="center" prop="getMoney">
        <template slot-scope="scope">
          <template v-if="scope.row.edit">
            <el-input class="edit-input" size="small" v-model="scope.row.getMoney"></el-input>
          </template>
          <span @click='innerEdit(scope.$index, scope.row,baoziList)' v-else>{{ scope.row.getMoney }}</span>
        </template>
      </el-table-column>
      <el-table-column label="当日营业额" align="center" prop="getAllMoney"/>
      <el-table-column label="当日进货金额" align="center" prop="putMoney" >
        <template slot-scope="scope">
          <template v-if="scope.row.edit">
            <el-input class="edit-input" size="small" v-model="scope.row.putMoney"></el-input>
          </template>
          <span @click='innerEdit(scope.$index, scope.row,baoziList)' v-else>{{ scope.row.putMoney }}</span>
        </template>
      </el-table-column>
      <el-table-column label="房租" align="center" prop="putHouseMoney" >
        <template slot-scope="scope">
          <template v-if="scope.row.edit">
            <el-input class="edit-input" size="small" v-model="scope.row.putHouseMoney"></el-input>
          </template>
          <span @click='innerEdit(scope.$index, scope.row,baoziList)' v-else>{{ scope.row.putHouseMoney }}</span>
        </template>
      </el-table-column>
      <el-table-column label="当日净利润" align="center" prop="actualMoney" />
      <el-table-column label="郭迎凤工资" align="center" prop="guoSalary" />
      <el-table-column label="耿世芳工资" align="center" prop="gengSalary" />
      <el-table-column label="进货内容" align="center" prop="putContent" >
        <template slot-scope="scope">
          <template v-if="scope.row.edit">
            <el-input class="ql-editor edit-input" size="small" v-model="scope.row.putContent" ></el-input>
          </template>
          <div v-html="scope.row.putContent" class="ql-editor" @click='innerEdit(scope.$index, scope.row,baoziList)' v-else="scope.row.putContent">{{ scope.row.putContent }}</div>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button v-if="scope.row.edit" type="success" v-hasPermi="['shop:baozi:edit']" @click="confirmEdit(scope.$index, scope.row)" size="small" >保存</el-button>
          <el-button v-else type="text" icon="el-icon-edit"  v-hasPermi="['shop:baozi:edit']" @click='handleUpdate(scope.row)' size="mini"> 编辑</el-button>
          <!--          <el-button-->
          <!--            size="mini"-->
          <!--            type="text"-->
          <!--            icon="el-icon-edit"-->
          <!--            @click="handleUpdate(scope.row)"-->
          <!--            v-hasPermi="['shop:huajia:edit']"-->
          <!--          >修改</el-button>-->
          <el-button v-if="scope.row.edit" type="warning" v-hasPermi="['shop:baozi:remove']" @click="cancelEdit(scope.row)" size="small" >取消</el-button>
          <el-button v-else type="text" icon="el-icon-edit"  @click='handleUpdate(scope.row)'  v-hasPermi="['shop:baozi:remove']" size="mini"> 删除</el-button>
          <!--          <el-button-->
          <!--            size="mini"-->
          <!--            type="text"-->
          <!--            icon="el-icon-delete"-->
          <!--            @click="handleDelete(scope.row)"-->
          <!--            v-hasPermi="['shop:huajia:remove']"-->
          <!--          >删除</el-button>-->
        </template>
      </el-table-column>
<!--      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">-->
<!--        <template slot-scope="scope">-->
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-edit"-->
<!--            @click="handleUpdate(scope.row)"-->
<!--            v-hasPermi="['shop:baozi:edit']"-->
<!--          >修改</el-button>-->
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-delete"-->
<!--            @click="handleDelete(scope.row)"-->
<!--            v-hasPermi="['shop:baozi:remove']"-->
<!--          >删除</el-button>-->
<!--        </template>-->
<!--      </el-table-column>-->
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改baozi对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="当天时间" prop="createDate">
          <el-date-picker clearable
            v-model="form.createDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择当天时间">
          </el-date-picker>
        </el-form-item>
<!--        <el-form-item label="当日星期" prop="day">-->
<!--          <el-input v-model="form.day" placeholder="请输入当日星期" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="店铺名称" prop="name">-->
<!--          <el-input v-model="form.name" placeholder="请输入店铺名称" />-->
<!--        </el-form-item>-->
        <el-form-item label="支付宝收款金额" prop="getPayMoney">
          <el-input v-model="form.getPayMoney" placeholder="请输入支付宝收款金额" />
        </el-form-item>
        <el-form-item label="微信收款金额" prop="getWechatMoney">
          <el-input v-model="form.getWechatMoney" placeholder="请输入微信收款金额" />
        </el-form-item>
        <el-form-item label="现金收款金额" prop="getMoney">
          <el-input v-model="form.getMoney" placeholder="请输入现金收款金额" />
        </el-form-item>
<!--        <el-form-item label="当日营业额" prop="getAllMoney">-->
<!--          <el-input v-model="form.getAllMoney" placeholder="请输入当日营业额" />-->
<!--        </el-form-item>-->
        <el-form-item label="当日进货金额" prop="putMoney">
          <el-input v-model="form.putMoney" placeholder="请输入当日进货金额"  />
        </el-form-item>
        <el-form-item label="房租" prop="putHouseMoney">
          <el-input v-model="form.putHouseMoney"  placeholder="请输入房租"  />
        </el-form-item>
<!--        <el-form-item label="当日净利润" prop="actualMoney">-->
<!--          <el-input v-model="form.actualMoney" placeholder="请输入当日净利润" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="郭迎凤工资" prop="guoSalary">-->
<!--          <el-input v-model="form.guoSalary" placeholder="请输入郭迎凤工资" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="耿世芳工资" prop="gengSalary">-->
<!--          <el-input v-model="form.gengSalary" placeholder="请输入耿世芳工资" />-->
<!--        </el-form-item>-->
        <el-form-item label="进货内容">
          <editor v-model="form.putContent" :min-height="192"/>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea"  placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <template>
      <el-dialog  @open="handleFenXi()"  :title="title1" :visible.sync="openFenXi"  width="1800px"  append-to-body>
        <div style="overflow: hidden">
          <div>
            <div id="newecharts" style="width:1800px;height:600px"  ref="chart"></div>
          </div>
          <div>
            <div id="newecharts2" style="width:500px;height:200px;float: left"  ref="chart2"></div>
            <div id="newecharts3" style="width:500px;height:200px;float: right"  ref="chart3"></div>
          </div>
        </div>
      </el-dialog>
    </template>
  </div>
</template>

<script>
import {listBaozi, getBaozi, delBaozi, addBaozi, updateBaozi, baoZiTongJi, baoziZheXianTongJi} from "@/api/shop/baozi";
import {red} from "chalk";
import {huaJiaZheXianTongJi, updateHuajia} from "@/api/shop/huajia";

export default {
  name: "Baozi",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // baozi表格数据
      baoziList: [],
      //统计数据
      tongji: {},
      // 弹出层标题
      title: "",
      title1: "当月数据报表",
      // 是否显示弹出层
      open: false,
      openFenXi: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        createDate: null,
        day: null,
        getAllMoney: null,
        queryMonth: null
      },
      defaultTemp :{
        fangzu : 70
      },
      addForm:{
        createDate:new Date()
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        id: [
          { required: true, message: "id不能为空", trigger: "blur" }
        ],
        createDate: [
          { required: true, message: "当天时间不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
    this.getTongJi();
  },
  methods: {
    cancelEdit(row){
      row.edit = false;
      this.getList();
      this.getTongJi();
    },

    innerEdit(index,row,rows){
      var isEdit = true;
      rows.forEach((col,index)=>{
        if(col.edit==true){
          isEdit = false;
        }
      })
      console.log(isEdit)
      if(isEdit){
        row.edit = true;
      }
    },
    confirmEdit(index,row){
      console.log(row)
      row.edit = false;
      updateBaozi(row).then(response => {
        this.$modal.msgSuccess("修改成功");
        this.open = false;
        this.getList();
        this.getTongJi();
      });
    },

    // 对列进行合算
    getSummaries(param) {
      const { columns, data } = param;
      const sums = [];
      columns.forEach((column, index) => {
        if (index === 0) {
          sums[index] = '合计';
          return;
        }
        const values = data.map(item => Number(item[column.property]));
        // 只对amount这一列进行总计核算。
        if (column.property === 'putMoney' || column.property === 'getAllMoney' || column.property === 'actualMoney' || column.property === 'putHouseMoney') {
          if (!values.every(value => isNaN(value))) {
            sums[index] = values.reduce((prev, curr) => {
              const value = Number(curr);
              if (!isNaN(value)) {
                return prev + curr;
              }else {
                return prev;
              }
            }, 0);
            sums[index] += ' 元';
          } else {
            sums[index] = '---'
          }
        }
      });
      return sums;
    },

    defaultDate() {
      this.reset();
      this.open = true;
      this.title = "添加拍品信息";
      var date = new Date();
      this.form.createDate = date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + (date.getDate());
    },
    columnStyle({ row, column, rowIndex, columnIndex }) {
      if (columnIndex == 7 || columnIndex == 10 ) {
        //第二三第四列的背景色就改变了2和3都是列数的下标
        if (columnIndex == 7 || columnIndex == 10) {
          //字体颜色
          return "background:yellow;color:red;cursor: pointer;font-weight:bold";
        }
        return "background:red";
      }

    },

    /** 查询包子统计数据 */
    getTongJi(){
      this.loading = true;
      baoZiTongJi(this.queryParams).then(response => {
        this.tongji = response.data;
        this.loading = false;
      });
    },
    /** 查询baozi列表 */
    getList() {
      this.loading = true;
      listBaozi(this.queryParams).then(response => {
        response.rows.forEach(item => {
          this.$set(item, 'edit', false)
        })
        this.baoziList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        createDate: null,
        day: null,
        name: null,
        getPayMoney: null,
        getWechatMoney: null,
        getMoney: null,
        getAllMoney: null,
        putMoney: null,
        putHouseMoney: null,
        actualMoney: null,
        guoSalary: null,
        gengSalary: null,
        putContent: null,
        remark: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
      this.getTongJi();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加包子铺营业额信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getBaozi(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改baozi";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateBaozi(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
              this.getTongJi();
            });
          } else {
            addBaozi(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
              this.getTongJi();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除baozi编号为"' + ids + '"的数据项？').then(function() {
        return delBaozi(ids);
      }).then(() => {
        this.getList();
        this.getTongJi();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('shop/baozi/export', {
        ...this.queryParams
      }, `baozi_${new Date().getTime()}.xlsx`)
    },
    initEcharts() {

      baoziZheXianTongJi(this.queryParams).then(response => {
        let data = response.data;
        this.date = data.dataList;
        this.actual = data.getActualList;
        this.all = data.getAllList;
        this.guo = data.guoList;
        this.shuo = data.shuoList;
        let myChart = this.$echarts.init(this.$refs.chart);
        // 绘制图表
        let option = {
          title: {
            text: '本月收入'
          },
          legend: {
            data: ['每天收入', '净利润', '耿工资', '凤工资']
          },
          tooltip: {},
          axisLabel : {
            interval : 0,
            rotate : 30,
            magin :10,
          },
          xAxis: {
            name: '日期',
            data: this.date
          },
          yAxis: {
            name: '金额'

          },
          series: [{
            name: '每天收入',
            type: 'line',
            data: this.all,
            itemStyle:{
              normal:{
                label:{
                  show:true //在每个上面显示当前值
                }
              }
            }
          },
            {
              name: '净利润',
              type: 'line',
              data: this.actual,
              itemStyle:{
                normal:{
                  label:{
                    show:true //在每个上面显示当前值
                  }
                }
              }
            },
            {
              name: '耿工资',
              type: 'line',
              data: this.shuo,
              itemStyle:{
                normal:{
                  label:{
                    show:true //在每个上面显示当前值
                  }
                }
              }
            },
            {
              name: '凤工资',
              type: 'line',
              data: this.guo,
              itemStyle:{
                normal:{
                  label:{
                    show:true //在每个上面显示当前值
                  }
                }
              }
            }
          ]
        };
        myChart.setOption(option)

        let myChart2 = this.$echarts.init(this.$refs.chart2);
        let option2 = {
          legend: {
            orient: 'vertical',
            x:'right',      //可设定图例在左、右、居中
            y:'center',
            data: [
              '房租',
              `净利润`,
              `进货`
            ],
          },
          title :{
            text: '本月总收入'+data.getAllMoney
          },
          series: [
            {
              type: 'pie',      //type为pie，表示图表为饼图
              radius: '55%',
              label: {
                show: true,
                formatter: "{b} : {c} ({d}%)" // b代表名称，c代表对应值，d代表百分比
              },
              data: [
                {
                  value: data.putHouseMoney,
                  name: '房租'
                },
                {
                  value: data.getActualMoney,
                  name: '净利润'
                },
                {
                  value: data.putAllMoney,
                  name: '进货'
                }
              ]
            }
          ]
        };
        myChart2.setOption(option2)

        let myChart3 = this.$echarts.init(this.$refs.chart3);
        let option3 = {
          legend: {
            orient: 'vertical',
            x:'right',      //可设定图例在左、右、居中
            y:'center',
            data: [
              '总工资',
              `耿



              工资`,
              `郭工资`
            ],
          },
          avoidLabelOverlap : false,
          title: {
            text: "总工资"+data.allSalary
          },
          series: [
            {
              type: 'pie',      //type为pie，表示图表为饼图
              radius: '55%',
              label: {
                show: true,
                formatter: "{b} : {c} ({d}%)" // b代表名称，c代表对应值，d代表百分比
              },
              data: [
                // {
                //   value: data.allSalary,
                //   name: '总工资'
                // },
                {
                  value: data.shuoSalary,
                  name: '耿工资'
                },
                {
                  value: data.guoSalary,
                  name: '郭工资'
                }
              ]
            }
          ]
        };
        myChart3.setOption(option3)
      });

      // 基于准备好的dom，初始化echarts实例

    },
    /** 数据分析操作 */
    handleFenXi() {
      this.$nextTick(() => {

        //  执行echarts方法
      })
      this.initEcharts()
    },
  }
};
</script>
