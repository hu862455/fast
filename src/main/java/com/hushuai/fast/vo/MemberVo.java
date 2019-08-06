package com.hushuai.fast.vo;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: shuaihu2
 * @Date: 2019/7/27
 * @Interface: MemberVo
 * @Description:
 */
public class MemberVo extends PageVo {
    private Integer id;
    private Integer memberLevelId;
    private String name;
    private String address;
    private String telephone;
    private Integer sex;
    private BigDecimal account;
    private BigDecimal totalAccount;
    private String password;
    private String levelName;
    private Double count;
    private BigDecimal recharge;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMemberLevelId() {
        return memberLevelId;
    }

    public void setMemberLevelId(Integer memberLevelId) {
        this.memberLevelId = memberLevelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public BigDecimal getAccount() {
        return account;
    }

    public void setAccount(BigDecimal account) {
        this.account = account;
    }

    public BigDecimal getTotalAccount() {
        return totalAccount;
    }

    public void setTotalAccount(BigDecimal totalAccount) {
        this.totalAccount = totalAccount;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public Double getCount() {
        return count;
    }

    public void setCount(Double count) {
        this.count = count;
    }

    public BigDecimal getRecharge() {
        return recharge;
    }

    public void setRecharge(BigDecimal recharge) {
        this.recharge = recharge;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MemberVo memberVo = (MemberVo) o;
        return Objects.equals(id, memberVo.id) &&
                Objects.equals(memberLevelId, memberVo.memberLevelId) &&
                Objects.equals(name, memberVo.name) &&
                Objects.equals(address, memberVo.address) &&
                Objects.equals(telephone, memberVo.telephone) &&
                Objects.equals(sex, memberVo.sex) &&
                Objects.equals(account, memberVo.account) &&
                Objects.equals(totalAccount, memberVo.totalAccount) &&
                Objects.equals(password, memberVo.password) &&
                Objects.equals(levelName, memberVo.levelName) &&
                Objects.equals(count, memberVo.count) &&
                Objects.equals(recharge, memberVo.recharge);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, memberLevelId, name, address, telephone, sex, account, totalAccount, password, levelName, count, recharge);
    }

    @Override
    public String toString() {
        return "MemberVo{" +
                "id=" + id +
                ", memberLevelId=" + memberLevelId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", telephone='" + telephone + '\'' +
                ", sex=" + sex +
                ", account=" + account +
                ", totalAccount=" + totalAccount +
                ", password='" + password + '\'' +
                ", levelName='" + levelName + '\'' +
                ", count=" + count +
                ", recharge=" + recharge +
                '}';
    }
}
