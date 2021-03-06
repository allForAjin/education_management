USE [master]
GO
/****** Object:  Database [Teaching_system]    Script Date: 2021/8/28 23:17:28 ******/
CREATE DATABASE [Teaching_system]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'Teaching_system', FILENAME = N'C:\Users\15485\Desktop\数据库原理\数据库原理课程设计\数据库文件\Teaching_system.mdf' , SIZE = 10240KB , MAXSIZE = 102400KB , FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'Teaching_system_log', FILENAME = N'C:\Users\15485\Desktop\数据库原理\数据库原理课程设计\数据库文件\Teaching_system.ldf' , SIZE = 10240KB , MAXSIZE = 102400KB , FILEGROWTH = 1024KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [Teaching_system] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [Teaching_system].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [Teaching_system] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [Teaching_system] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [Teaching_system] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [Teaching_system] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [Teaching_system] SET ARITHABORT OFF 
GO
ALTER DATABASE [Teaching_system] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [Teaching_system] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [Teaching_system] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [Teaching_system] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [Teaching_system] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [Teaching_system] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [Teaching_system] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [Teaching_system] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [Teaching_system] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [Teaching_system] SET  ENABLE_BROKER 
GO
ALTER DATABASE [Teaching_system] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [Teaching_system] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [Teaching_system] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [Teaching_system] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [Teaching_system] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [Teaching_system] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [Teaching_system] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [Teaching_system] SET RECOVERY FULL 
GO
ALTER DATABASE [Teaching_system] SET  MULTI_USER 
GO
ALTER DATABASE [Teaching_system] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [Teaching_system] SET DB_CHAINING OFF 
GO
ALTER DATABASE [Teaching_system] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [Teaching_system] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [Teaching_system] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [Teaching_system] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'Teaching_system', N'ON'
GO
ALTER DATABASE [Teaching_system] SET QUERY_STORE = OFF
GO
USE [Teaching_system]
GO
/****** Object:  ColumnMasterKey [CMK_Auto1]    Script Date: 2021/8/28 23:17:28 ******/
CREATE COLUMN MASTER KEY [CMK_Auto1]
WITH
(
	KEY_STORE_PROVIDER_NAME = N'MSSQL_CERTIFICATE_STORE',
	KEY_PATH = N'CurrentUser/my/6A534683DF1143524F6C2DB428F025163F0A4109'
)
GO
/****** Object:  ColumnEncryptionKey [CEK_Auto1]    Script Date: 2021/8/28 23:17:28 ******/
CREATE COLUMN ENCRYPTION KEY [CEK_Auto1]
WITH VALUES
(
	COLUMN_MASTER_KEY = [CMK_Auto1],
	ALGORITHM = 'RSA_OAEP',
	ENCRYPTED_VALUE = 0x016E000001630075007200720065006E00740075007300650072002F006D0079002F003600610035003300340036003800330064006600310031003400330035003200340066003600630032006400620034003200380066003000320035003100360033006600300061003400310030003900214D3C961DA0A41D2829D79012BDB7039C8ED61AF90D47ADFFCD286A025BE816CAE7E9B90A6C9A88164E8E07420628C1D35BF09F84C13246C612849E6CF0FE49F676A10A4374CD2C6531CCD17015EB012CDEAB4267949A5CA9209E6729B3F752866D47B564A5D3694CDF5CCA332E6265D5E247C1454E6065AF440F21353EA8A0EB7747F0DBA85868669E24B5D49991726680275C465E3B22D86C1AE8076F82F8928CA3CFE20860ECE5D168D38F4D8867788F73F59388029798C09A9CFB7F3FCD292E084BB2C19E4B2F9C90D9B43413BB6637B6BA5820EFF38E749C1F9A42AA648EA1CBA352EA2B9C50733B407324798A7380BD54A921AA2E38402B5119C8ACB21FA63DE634D88229B8DE9EA0E4C6A0EB581DE57662DFD5CA84B16A3CFDE29ED7F05F1DCC0C130BECA5C0C0C59D830B17A5699952BB1FE9DA215BB8CF368A9959B8C4F2E125BB9F3F4276F09FD49C77A5C5FA422F855D37CC58F35688EF70380D389E440B45C6080DF5819D1145818EC254DE39F177A907A5F5C55E5DF66ABECE23610A2B2A02D71A8A39FA44EEA92B3137CE9225C559F32EE94B067DA9EF76EEBA8CEBF24DDBCF0DBC38CB4A334D01D79BF5498AB81D17DE03C754C20A0B21F7CE26C22F4688C4B66DFD18EB339E70FE375D9BEEFB4A7BE5C57F09F29478F867C254CA5EB875F026C189A9B82A74AC5DFAAAD551062F6AF3699CBC267A1DBC79
)
GO
/****** Object:  Table [dbo].[SC]    Script Date: 2021/8/28 23:17:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SC](
	[Sno] [char](8) NOT NULL,
	[Cno] [char](3) NOT NULL,
	[Grade] [smallint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[Sno] ASC,
	[Cno] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Teacher]    Script Date: 2021/8/28 23:17:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Teacher](
	[Tno] [char](8) NOT NULL,
	[Tname] [varchar](10) NOT NULL,
	[Sex] [char](2) NOT NULL,
	[Title] [varchar](10) NOT NULL,
	[Birth] [smalldatetime] NULL,
	[Entry_date] [smalldatetime] NULL,
	[Telephone] [char](11) NULL,
 CONSTRAINT [PK__Teacher__C450026DF7D1BAFB] PRIMARY KEY CLUSTERED 
(
	[Tno] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[College]    Script Date: 2021/8/28 23:17:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[College](
	[Gno] [char](3) NOT NULL,
	[Gname] [varchar](15) NOT NULL,
 CONSTRAINT [PK__College__C51FB5ADE74D0A7C] PRIMARY KEY CLUSTERED 
(
	[Gno] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Teach]    Script Date: 2021/8/28 23:17:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Teach](
	[Cno] [char](3) NOT NULL,
	[Tno] [char](8) NOT NULL,
 CONSTRAINT [PK_Teach] PRIMARY KEY CLUSTERED 
(
	[Tno] ASC,
	[Cno] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Course]    Script Date: 2021/8/28 23:17:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Course](
	[Cno] [char](3) NOT NULL,
	[Cname] [varchar](20) NOT NULL,
	[Period] [int] NOT NULL,
	[Credit] [int] NOT NULL,
	[Semester] [varchar](20) NOT NULL,
	[Amount] [int] NULL,
	[Gno] [char](3) NOT NULL,
 CONSTRAINT [PK__Course__C1FE6373AEF3A497] PRIMARY KEY CLUSTERED 
(
	[Cno] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  View [dbo].[elect_amount]    Script Date: 2021/8/28 23:17:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create view [dbo].[elect_amount]
as
	select Course.Cno,count(Sno) as elect_amount 
		from Course left join SC
		on Course.Cno=SC.Cno
		group by Course.Cno;
GO
/****** Object:  View [dbo].[Course_info]    Script Date: 2021/8/28 23:17:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create view [dbo].[Course_info]
as
	select Course.Cno,Course.Cname,Course.Period,
	Course.Credit,Course.Semester,College.Gname,Amount,Teacher.Tname,Teacher.Tno,a.elect_amount
		from dbo.Course left join dbo.Teach
		on Teach.Cno=Course.Cno
		left join dbo.Teacher 
		on Teacher.Tno=Teach.Tno 
		left join dbo.College
		on College.Gno=Course.Gno
		left join elect_amount as a on a.Cno=Course.Cno;
GO
/****** Object:  View [dbo].[student_course_selected]    Script Date: 2021/8/28 23:17:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create view [dbo].[student_course_selected]
as
	select Course_info.*,Sno
		from SC left join Course_info
		on SC.Cno=Course_info.Cno;
GO
/****** Object:  Table [dbo].[Student_department]    Script Date: 2021/8/28 23:17:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Student_department](
	[Sno] [char](8) NOT NULL,
	[Class_no] [char](3) NOT NULL,
 CONSTRAINT [PK__Student___1116847731907DBF] PRIMARY KEY CLUSTERED 
(
	[Sno] ASC,
	[Class_no] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Student]    Script Date: 2021/8/28 23:17:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Student](
	[Sno] [char](8) NOT NULL,
	[Sname] [varchar](10) NULL,
	[Sex] [char](3) NOT NULL,
	[Birth] [smalldatetime] NULL,
	[Eroll_date] [smalldatetime] NULL,
	[Telephone] [char](11) NULL,
 CONSTRAINT [PK__Student__CA1FE464D82CA885] PRIMARY KEY CLUSTERED 
(
	[Sno] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  View [dbo].[Student_score_info]    Script Date: 2021/8/28 23:17:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create view [dbo].[Student_score_info]
as
	select SC.Sno,SC.Cno,Sname,Cname,Credit,Semester,Gname,Grade,Period,Class_no
		from SC left join Student
		on SC.Sno=Student.Sno
		left join Course
		on SC.Cno=Course.Cno
		left join College
		on College.Gno=Course.Gno
		left join Student_department
		on Student.Sno=Student_department.Sno;
GO
/****** Object:  View [dbo].[student_score_count]    Script Date: 2021/8/28 23:17:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create view [dbo].[student_score_count]
as
	select cast(cast(sum(case when Grade between 90 and 100 then 1 else 0 end)*100/count(1)*1.0 as float)as varchar)+'%' as '优',
		cast(cast(sum(case when Grade between 80 and 90 then 1 else 0 end)*100/count(1)*1.0 as float)as varchar)+'%' as'良',
		cast(cast(sum(case when Grade between 70 and 80 then 1 else 0 end)*100/count(1)*1.0 as float)as varchar)+'%' as'中等',
		cast(cast(sum(case when Grade between 60 and 70 then 1 else 0 end)*100/count(1)*1.0 as float)as varchar)+'%' as '及格',
		cast(cast(sum(case when Grade <60 then 1 else 0 end)*100/count(1)*1.0 as float)as varchar)+'%' as'不及格',
		sum(case when Grade between 90 and 100 then 1 else 0 end) as '优人数',
		sum(case when Grade between 80 and 90 then 1 else 0 end) as '良人数',
		sum(case when Grade between 70 and 80 then 1 else 0 end) as '中等人数',
		sum(case when Grade between 60 and 70 then 1 else 0 end) as '及格人数',
		sum(case when Grade <60 then 1 else 0 end) as '不及格人数'
			from Student_score_info
GO
/****** Object:  View [dbo].[College_info]    Script Date: 2021/8/28 23:17:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create view [dbo].[College_info]
as
	select Gno,Gname
		from College;
GO
/****** Object:  View [dbo].[Score_count]    Script Date: 2021/8/28 23:17:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create view [dbo].[Score_count]
as
	select SC.Sno,Sname,Count(*) as amount,sum(Grade) as sum_Score,avg(Grade) as average,sum(Credit) as sum_credit
		from SC left join Student
		on SC.Sno=Student.Sno
		left join Course
		on SC.Cno=Course.Cno
		group by SC.Sno,Sname;
GO
/****** Object:  Table [dbo].[Class]    Script Date: 2021/8/28 23:17:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Class](
	[Class_no] [char](3) NOT NULL,
	[Amount] [int] NOT NULL,
	[Dno] [char](3) NOT NULL,
 CONSTRAINT [PK__Class__B096013CCBD42DD7] PRIMARY KEY CLUSTERED 
(
	[Class_no] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Sdept]    Script Date: 2021/8/28 23:17:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Sdept](
	[Dno] [char](3) NOT NULL,
	[Dname] [varchar](20) NOT NULL,
	[Gno] [char](3) NOT NULL,
 CONSTRAINT [PK__Sdept__C030857AFDE28007] PRIMARY KEY CLUSTERED 
(
	[Dno] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
 CONSTRAINT [IX_Sdept] UNIQUE NONCLUSTERED 
(
	[Dname] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  View [dbo].[student_info]    Script Date: 2021/8/28 23:17:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create view [dbo].[student_info]
as
	select Student.Sno,Sname,Sex,(year(getdate())-year(Birth)) as age,
	Telephone,Student_department.Class_no,Dname,Gname,Birth,Eroll_Date
		from Student,Student_department,Class,Sdept,College
		where Student.Sno=Student_department.Sno and Student_department.Class_no=Class.Class_no
		and Class.Dno=Sdept.Dno and Sdept.Gno=College.Gno;
GO
/****** Object:  View [dbo].[sdept_info]    Script Date: 2021/8/28 23:17:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create view [dbo].[sdept_info]
as
	select Dno,Dname,Gname,College.Gno
	from Sdept left join College
	on Sdept.Gno=College.Gno;
GO
/****** Object:  Table [dbo].[Office]    Script Date: 2021/8/28 23:17:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Office](
	[Office_no] [char](5) NOT NULL,
	[Office_name] [varchar](15) NOT NULL,
	[Telephone] [varchar](11) NULL,
	[Dno] [char](3) NOT NULL,
 CONSTRAINT [PK__Office__2206091966C6E93B] PRIMARY KEY CLUSTERED 
(
	[Office_no] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Teacher_department]    Script Date: 2021/8/28 23:17:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Teacher_department](
	[Tno] [char](8) NOT NULL,
	[Office_no] [char](5) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[Tno] ASC,
	[Office_no] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  View [dbo].[teacher_all_info]    Script Date: 2021/8/28 23:17:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create view [dbo].[teacher_all_info]
as
	select Teacher.*,Office.Office_name,Sdept.Dname,College.Gname,(year(getdate())-year(birth)) as age
		from Teacher left join Teacher_department
			on Teacher.Tno=Teacher_department.Tno
			left join Office
			on Teacher_department.Office_no=Office.Office_no
			left join Sdept
			on Office.Dno=Sdept.Dno
			left join College
			on Sdept.Gno=College.Gno;
GO
/****** Object:  View [dbo].[class_sdept_college]    Script Date: 2021/8/28 23:17:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create view [dbo].[class_sdept_college]
as
	select College.Gno,Class_no,Dname,Gname
		from Class,Sdept,College
		where Class.Dno=Sdept.Dno and Sdept.Gno=College.Gno;
GO
/****** Object:  View [dbo].[office_sdept_college]    Script Date: 2021/8/28 23:17:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create view [dbo].[office_sdept_college]
as
	select Office_name,Dname,Gname
		from Office,Sdept,College
		where Office.Dno=Sdept.Dno and Sdept.Gno=College.Gno;
GO
/****** Object:  Table [dbo].[AdminUser]    Script Date: 2021/8/28 23:17:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[AdminUser](
	[UserName] [varchar](10) NOT NULL,
	[Password] [varchar](16) NOT NULL,
 CONSTRAINT [PK_User] PRIMARY KEY CLUSTERED 
(
	[UserName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [index_Cname]    Script Date: 2021/8/28 23:17:28 ******/
CREATE NONCLUSTERED INDEX [index_Cname] ON [dbo].[Course]
(
	[Cname] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [NonClusteredIndex-20210701-231309]    Script Date: 2021/8/28 23:17:28 ******/
CREATE NONCLUSTERED INDEX [NonClusteredIndex-20210701-231309] ON [dbo].[Teach]
(
	[Tno] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [index_Tname]    Script Date: 2021/8/28 23:17:28 ******/
CREATE NONCLUSTERED INDEX [index_Tname] ON [dbo].[Teacher]
(
	[Tname] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [IX_Teach]    Script Date: 2021/8/28 23:17:28 ******/
CREATE NONCLUSTERED COLUMNSTORE INDEX [IX_Teach] ON [dbo].[Teach]
(
	[Cno]
)WITH (DROP_EXISTING = OFF, COMPRESSION_DELAY = 0) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Class] ADD  CONSTRAINT [DF__Class__Amount__33D4B598]  DEFAULT ((0)) FOR [Amount]
GO
ALTER TABLE [dbo].[SC] ADD  CONSTRAINT [DF__SC__Grade__5535A963]  DEFAULT ((0)) FOR [Grade]
GO
ALTER TABLE [dbo].[Student] ADD  CONSTRAINT [DF__Student__Sex__24927208]  DEFAULT ('男') FOR [Sex]
GO
ALTER TABLE [dbo].[Teacher] ADD  CONSTRAINT [DF__Teacher__Sex__38996AB5]  DEFAULT ('男') FOR [Sex]
GO
ALTER TABLE [dbo].[Teacher] ADD  CONSTRAINT [DF__Teacher__Title__398D8EEE]  DEFAULT ('讲师') FOR [Title]
GO
ALTER TABLE [dbo].[Class]  WITH CHECK ADD  CONSTRAINT [FK__Class__Dno__34C8D9D1] FOREIGN KEY([Dno])
REFERENCES [dbo].[Sdept] ([Dno])
GO
ALTER TABLE [dbo].[Class] CHECK CONSTRAINT [FK__Class__Dno__34C8D9D1]
GO
ALTER TABLE [dbo].[Course]  WITH CHECK ADD  CONSTRAINT [FK_Course_Gno] FOREIGN KEY([Gno])
REFERENCES [dbo].[College] ([Gno])
GO
ALTER TABLE [dbo].[Course] CHECK CONSTRAINT [FK_Course_Gno]
GO
ALTER TABLE [dbo].[Office]  WITH CHECK ADD  CONSTRAINT [FK__Office__Dno__300424B4] FOREIGN KEY([Dno])
REFERENCES [dbo].[Sdept] ([Dno])
GO
ALTER TABLE [dbo].[Office] CHECK CONSTRAINT [FK__Office__Dno__300424B4]
GO
ALTER TABLE [dbo].[SC]  WITH CHECK ADD  CONSTRAINT [FK__SC__Cno__5441852A] FOREIGN KEY([Cno])
REFERENCES [dbo].[Course] ([Cno])
GO
ALTER TABLE [dbo].[SC] CHECK CONSTRAINT [FK__SC__Cno__5441852A]
GO
ALTER TABLE [dbo].[SC]  WITH CHECK ADD  CONSTRAINT [FK__SC__Sno__534D60F1] FOREIGN KEY([Sno])
REFERENCES [dbo].[Student] ([Sno])
GO
ALTER TABLE [dbo].[SC] CHECK CONSTRAINT [FK__SC__Sno__534D60F1]
GO
ALTER TABLE [dbo].[Sdept]  WITH CHECK ADD  CONSTRAINT [FK__Sdept__Gno__2D27B809] FOREIGN KEY([Gno])
REFERENCES [dbo].[College] ([Gno])
GO
ALTER TABLE [dbo].[Sdept] CHECK CONSTRAINT [FK__Sdept__Gno__2D27B809]
GO
ALTER TABLE [dbo].[Student_department]  WITH CHECK ADD  CONSTRAINT [FK__Student_d__Class__412EB0B6] FOREIGN KEY([Class_no])
REFERENCES [dbo].[Class] ([Class_no])
GO
ALTER TABLE [dbo].[Student_department] CHECK CONSTRAINT [FK__Student_d__Class__412EB0B6]
GO
ALTER TABLE [dbo].[Student_department]  WITH CHECK ADD  CONSTRAINT [FK__Student_dep__Sno__403A8C7D] FOREIGN KEY([Sno])
REFERENCES [dbo].[Student] ([Sno])
GO
ALTER TABLE [dbo].[Student_department] CHECK CONSTRAINT [FK__Student_dep__Sno__403A8C7D]
GO
ALTER TABLE [dbo].[Teach]  WITH CHECK ADD  CONSTRAINT [FK__Teach__Cno__4CA06362] FOREIGN KEY([Cno])
REFERENCES [dbo].[Course] ([Cno])
GO
ALTER TABLE [dbo].[Teach] CHECK CONSTRAINT [FK__Teach__Cno__4CA06362]
GO
ALTER TABLE [dbo].[Teach]  WITH CHECK ADD  CONSTRAINT [FK__Teach__Tno__4D94879B] FOREIGN KEY([Tno])
REFERENCES [dbo].[Teacher] ([Tno])
GO
ALTER TABLE [dbo].[Teach] CHECK CONSTRAINT [FK__Teach__Tno__4D94879B]
GO
ALTER TABLE [dbo].[Teacher_department]  WITH CHECK ADD  CONSTRAINT [FK__Teacher_d__Offic__49C3F6B7] FOREIGN KEY([Office_no])
REFERENCES [dbo].[Office] ([Office_no])
GO
ALTER TABLE [dbo].[Teacher_department] CHECK CONSTRAINT [FK__Teacher_d__Offic__49C3F6B7]
GO
ALTER TABLE [dbo].[Class]  WITH CHECK ADD  CONSTRAINT [CK__Class__Amount__35BCFE0A] CHECK  (([Amount]>=(0)))
GO
ALTER TABLE [dbo].[Class] CHECK CONSTRAINT [CK__Class__Amount__35BCFE0A]
GO
ALTER TABLE [dbo].[College]  WITH CHECK ADD  CONSTRAINT [CK__College__Gname__2A4B4B5E] CHECK  (([Gname] like '%学院'))
GO
ALTER TABLE [dbo].[College] CHECK CONSTRAINT [CK__College__Gname__2A4B4B5E]
GO
ALTER TABLE [dbo].[Course]  WITH CHECK ADD  CONSTRAINT [CK__Course__Amount__45F365D3] CHECK  (([Amount]>(0) AND [Amount]<(200)))
GO
ALTER TABLE [dbo].[Course] CHECK CONSTRAINT [CK__Course__Amount__45F365D3]
GO
ALTER TABLE [dbo].[Course]  WITH CHECK ADD  CONSTRAINT [CK__Course__Credit__44FF419A] CHECK  (([Credit]>(0) AND [Credit]<(20)))
GO
ALTER TABLE [dbo].[Course] CHECK CONSTRAINT [CK__Course__Credit__44FF419A]
GO
ALTER TABLE [dbo].[Course]  WITH CHECK ADD  CONSTRAINT [CK__Course__Period__440B1D61] CHECK  (([Period]>(0) AND [Period]<(100)))
GO
ALTER TABLE [dbo].[Course] CHECK CONSTRAINT [CK__Course__Period__440B1D61]
GO
ALTER TABLE [dbo].[Course]  WITH CHECK ADD  CONSTRAINT [CK__Course__Semester__46E78A0C] CHECK  (([Semester] like '[1-3][0-9][0-9][0-9]学年第[一二]学期'))
GO
ALTER TABLE [dbo].[Course] CHECK CONSTRAINT [CK__Course__Semester__46E78A0C]
GO
ALTER TABLE [dbo].[Office]  WITH CHECK ADD  CONSTRAINT [CK__Office__Telephon__30F848ED] CHECK  (([Telephone] like '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]'))
GO
ALTER TABLE [dbo].[Office] CHECK CONSTRAINT [CK__Office__Telephon__30F848ED]
GO
ALTER TABLE [dbo].[SC]  WITH CHECK ADD  CONSTRAINT [CK__SC__Grade__5629CD9C] CHECK  (([Grade]>=(0) AND [Grade]<=(100)))
GO
ALTER TABLE [dbo].[SC] CHECK CONSTRAINT [CK__SC__Grade__5629CD9C]
GO
ALTER TABLE [dbo].[Student]  WITH CHECK ADD  CONSTRAINT [CK__Student__267ABA7A] CHECK  (([Birth]<[Eroll_date] AND [Eroll_date]<getdate()))
GO
ALTER TABLE [dbo].[Student] CHECK CONSTRAINT [CK__Student__267ABA7A]
GO
ALTER TABLE [dbo].[Student]  WITH CHECK ADD  CONSTRAINT [CK__Student__Sex__25869641] CHECK  (([Sex]='女' OR [Sex]='男'))
GO
ALTER TABLE [dbo].[Student] CHECK CONSTRAINT [CK__Student__Sex__25869641]
GO
ALTER TABLE [dbo].[Student]  WITH CHECK ADD  CONSTRAINT [CK__Student__Telepho__276EDEB3] CHECK  (([Telephone] like '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]'))
GO
ALTER TABLE [dbo].[Student] CHECK CONSTRAINT [CK__Student__Telepho__276EDEB3]
GO
ALTER TABLE [dbo].[Teacher]  WITH CHECK ADD  CONSTRAINT [CK__Teacher__3C69FB99] CHECK  (([Birth]<[Entry_date] AND [Entry_date]<getdate()))
GO
ALTER TABLE [dbo].[Teacher] CHECK CONSTRAINT [CK__Teacher__3C69FB99]
GO
ALTER TABLE [dbo].[Teacher]  WITH CHECK ADD  CONSTRAINT [CK__Teacher__Sex__3B75D760] CHECK  (([Sex]='女' OR [Sex]='男'))
GO
ALTER TABLE [dbo].[Teacher] CHECK CONSTRAINT [CK__Teacher__Sex__3B75D760]
GO
ALTER TABLE [dbo].[Teacher]  WITH CHECK ADD  CONSTRAINT [CK__Teacher__Telepho__3A81B327] CHECK  (([Telephone] like '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]'))
GO
ALTER TABLE [dbo].[Teacher] CHECK CONSTRAINT [CK__Teacher__Telepho__3A81B327]
GO
ALTER TABLE [dbo].[Teacher]  WITH CHECK ADD  CONSTRAINT [CK__Teacher__Title__3D5E1FD2] CHECK  (([Title]='讲师' OR [Title]='高级讲师' OR [Title]='副教授' OR [Title]='教授'))
GO
ALTER TABLE [dbo].[Teacher] CHECK CONSTRAINT [CK__Teacher__Title__3D5E1FD2]
GO
/****** Object:  StoredProcedure [dbo].[class_existed]    Script Date: 2021/8/28 23:17:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[class_existed]
	@Class_no char(10),@flag int output
as
	select @flag=count(*)
		from Class
		where Class_no=@Class_no;
GO
/****** Object:  StoredProcedure [dbo].[college_existed]    Script Date: 2021/8/28 23:17:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[college_existed]
	@Gno char(5),@flag int output
as
	select @flag=count(*)
		from College
		where Gno=@Gno;
GO
/****** Object:  StoredProcedure [dbo].[course_delete_p]    Script Date: 2021/8/28 23:17:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[course_delete_p]
	@Cno char(3)
as
	delete from Course
		where Cno=@Cno;
GO
/****** Object:  StoredProcedure [dbo].[course_existed]    Script Date: 2021/8/28 23:17:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[course_existed]
	@Cno char(3),@flag int output
as
	select @flag=count(*)
		from Course
		where Cno=@Cno;
GO
/****** Object:  StoredProcedure [dbo].[course_insert]    Script Date: 2021/8/28 23:17:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[course_insert]
	@Cno char(3),@Cname varchar(20),@Period int,
	@Credit int,@Semester varchar(20),@Amount int,@Gno char(5)
as
	insert into Course
		values(@Cno,@Cname,@Period,@Credit,@Semester,@Amount,@Gno);
GO
/****** Object:  StoredProcedure [dbo].[course_SC_existed]    Script Date: 2021/8/28 23:17:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[course_SC_existed]
	@Cno char(3),@flag int output
as
	select @flag=count(*)
		from SC
		where Cno=@Cno
		group by Cno;
GO
/****** Object:  StoredProcedure [dbo].[course_select]    Script Date: 2021/8/28 23:17:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[course_select]
	@cno char(3),@cname varchar(10),@semester varchar(20),@Gname varchar(10)
as
	select * from Course_info
		where (@cno is null or Cno=@cno) and 
		(@cname is null or Cname=@Cname) and
		(@semester is null or Semester=@semester) and
		(@Gname is null or Gname=@Gname);
GO
/****** Object:  StoredProcedure [dbo].[course_teach_existed]    Script Date: 2021/8/28 23:17:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[course_teach_existed]
	@Cno char(3),@flag int output
as
	select @flag=count(Cno)
		from Teach
		where Cno=@Cno
GO
/****** Object:  StoredProcedure [dbo].[office_existed]    Script Date: 2021/8/28 23:17:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[office_existed]
	@Office_no char(5),@flag int output
as
	select @flag=count(*)
		from Office
		where Office_no=@Office_no;
GO
/****** Object:  StoredProcedure [dbo].[sc_insert]    Script Date: 2021/8/28 23:17:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[sc_insert]
	@Sno char(8),@Cno char(3)
as
	insert into SC (Sno,Cno)
		values(@Sno,@Cno);
GO
/****** Object:  StoredProcedure [dbo].[sdept_existed]    Script Date: 2021/8/28 23:17:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[sdept_existed]
	@Dno char(5),@flag int output
as
	select @flag=count(*)
		from Sdept
		where Dno=@Dno;
GO
/****** Object:  StoredProcedure [dbo].[stu_delete_p]    Script Date: 2021/8/28 23:17:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[stu_delete_p]
	@Sno char(8)
as
	delete from Student
		where Sno=@Sno;
GO
/****** Object:  StoredProcedure [dbo].[stu_insert]    Script Date: 2021/8/28 23:17:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[stu_insert]
	@Sno char(8),@Sname varchar(10),@Sex char(10),
	@Birth smalldatetime,@Eroll_date smalldatetime,@Telephone char(11),
	@Class_no char(10)
as
	--declare @flag int;
	--exec student_existed @Sno,@flag;
	--if (@flag!=1)
		--begin
		insert into Student
			values(@Sno,@Sname,@Sex,@Birth,@Eroll_date,@Telephone);
		insert into Student_department
			values(@Sno,@Class_no);
	--end
GO
/****** Object:  StoredProcedure [dbo].[stu_SC_existed]    Script Date: 2021/8/28 23:17:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[stu_SC_existed]
	@Sno char(8),@flag int output
as
	select @flag=count(*)
		from SC
		where Sno=@Sno
		group by Sno;
GO
/****** Object:  StoredProcedure [dbo].[student_existed]    Script Date: 2021/8/28 23:17:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[student_existed]
	@Sno char(8),@flag int output
as
	select @flag=count(Sno)
		from Student
		where Sno=@Sno;
GO
/****** Object:  StoredProcedure [dbo].[student_select_course]    Script Date: 2021/8/28 23:17:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[student_select_course]
	@Sno char(8),@Cno char(8),@flag int output
as
	select @flag=count(*)
		from SC
		where Sno=@Sno and Cno=@Cno
		group by Sno,Cno;
GO
/****** Object:  StoredProcedure [dbo].[student_update]    Script Date: 2021/8/28 23:17:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[student_update]
@Sno char(8),@Sname varchar(10),@Sex char(10),
	@Birth smalldatetime,@Eroll_date smalldatetime,@Telephone char(11),
	@Class_no char(3)
as
	update Student set Sno=@Sno,Sname=@Sname,Sex=@sex,Birth=@Birth,Eroll_date=@Eroll_date,Telephone=@Telephone where Sno=@Sno;
	update Student_department set Class_no=@Class_no where Sno=@Sno;
GO
/****** Object:  StoredProcedure [dbo].[teach_insert]    Script Date: 2021/8/28 23:17:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[teach_insert]
	@Cno char(3),@Tno char(8)
as
	insert into Teach
		values(@Cno,@Tno);
GO
/****** Object:  StoredProcedure [dbo].[teacher_delete_p]    Script Date: 2021/8/28 23:17:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[teacher_delete_p]
	@Tno char(8)
as
	delete from Teacher
		where Tno=@Tno;
GO
/****** Object:  StoredProcedure [dbo].[teacher_existed]    Script Date: 2021/8/28 23:17:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[teacher_existed]
	@Tno char(8),@flag int output
as
	select @flag=count(Tno)
		from Teacher
		where Tno=@Tno;
GO
/****** Object:  StoredProcedure [dbo].[teacher_insert]    Script Date: 2021/8/28 23:17:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[teacher_insert]
	@Tno char(8),@Tname varchar(10),@Sex varchar(3),@Title varchar(10),
	@Birth smalldatetime,@Entry_date smalldatetime,@Telephone varchar(11),
	@Office_no char(5)
as
	declare @flag int;
	exec teacher_existed @Tno,@flag;
	if (@flag!=1)
	begin
		insert into Teacher
			values(@Tno,@Tname,@Sex,@Title,@Birth,@Entry_date,@Telephone);
		insert into Teacher_department
			values(@Tno,@Office_no);
	end
GO
/****** Object:  StoredProcedure [dbo].[teacher_teach_existed]    Script Date: 2021/8/28 23:17:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[teacher_teach_existed]
	@Tno char(8),@flag int output
as
	select @flag=count(*)
		from Teach
		where Tno=@Tno
		group by Tno;
GO
/****** Object:  StoredProcedure [dbo].[teacher_update]    Script Date: 2021/8/28 23:17:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[teacher_update]
	@Tno char(8),@Tname varchar(8),@sex char(2),@title varchar(10),
	@birth smalldatetime,@entry smalldatetime,@phone char(11),@office varchar(15)
as
	update Teacher set Tno=@Tno,Tname=@Tname,Sex=@sex,Title=@title,Birth=@birth,
	Entry_date=@entry,Telephone=@phone where Tno=@Tno;
	update Teacher_department set Office_no=(select Office_no from Office where Office_name=@office)
	where Tno=@Tno;
GO
USE [master]
GO
ALTER DATABASE [Teaching_system] SET  READ_WRITE 
GO
