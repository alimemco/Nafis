package com.ali.rnp.nafis.view.DataModel;

import java.util.ArrayList;
import java.util.List;

public class DataGenrator {

    public static List<Question> getQuestions() {

        List<Question> questions = new ArrayList<>();

        for (int i = 1; i <= 17; i++) {
            Question question = new Question();
            question.setId(i);

            switch (i) {

                case 1:
                    question.setQuestion("آیا تحت نظر پزشک پوست و مو هستید؟");
                    question.setAnswer1("بله");
                    question.setAnswer2("خیر");
                    question.setExplanation("علت را توضیح دهید..");
                    break;

                case 2:
                    question.setQuestion("آیا از داروی خاصی استفاده می کنید؟");
                    question.setAnswer1("بله");
                    question.setAnswer2("خیر");
                    question.setExplanation("نام دارو..");
                    break;

                case 3:
                    question.setQuestion("آیا سابقه حساسیت و آلرژی نسبت به محصولات بهداشتی، آرایشی یا مراقبتی پوست و مو دارید؟");
                    question.setAnswer1("بله");
                    question.setAnswer2("خیر");
                    break;

                case 4:
                    question.setQuestion("آیا در طول روز در معرض آلودگی هوا، نور مستقیم خورشید و یا مواد شیمیایی خاصی هستید؟");
                    question.setAnswer1("بله");
                    question.setAnswer2("خیر");
                    break;

                case 5:
                    question.setQuestion("آیا تاکنون اعمالی مانند لیزر، بوتاکس، تزریق ژل، سولار و یا مانند این موارد روی پوست خود انجام داده اید؟");
                    question.setAnswer1("بله");
                    question.setAnswer2("خیر");
                    break;

                case 6:
                    question.setQuestion("چه شرایط یا مسائل دیگری در مورد پوست و موی شما وجود دارد که لازم به توضیح می باشد؟");
                    question.setExplanation("توضیح دهید..");
                    break;

                case 7:
                    question.setQuestion("در یکسال گذشته از کدامیک از محصولات زیر استفاده کرده اید؟");
                    question.setAnswer1("اسکراب");
                    question.setAnswer2("ماسک ورقه ای");
                    question.setAnswer3("ژل شستشو ، شیرپاک کن");
                    question.setAnswer4("کرم دور چشم");
                    question.setAnswer5("کرم روز یا شب");
                    question.setAnswer6("ضد چروک");
                    question.setAnswer7("ضد لک");
                    question.setAnswer8("ضدآفتاب");
                    question.setAnswer9("ضد جوش یا آکنه");
                    question.setExplanation("علت را توضیح دهید..");
                    break;

                case 8:
                    question.setQuestion("کدامیک از حالات زیر را روی پوست صورت خود تجربه کرده اید؟");
                    question.setAnswer1("خشکی و کشیدگی پوست صورت پس از شستشو");
                    question.setAnswer2("نیاز شدید به کرم مرطوب کننده در طول روز");
                    question.setAnswer3("پوسته پوسته شدن یا خارش پوست مخصوصا در فصل سرد");
                    question.setAnswer4("حساس بودن پوست به هر عامل خارجی");
                    question.setAnswer5("خارش، سوزش و یا قرمز شدن پوست پس از استفاده از لوازم آرایشی");
                    question.setAnswer6("چرب شدن سریع پوست و برق زدن ناحیه پیشانی");
                    question.setAnswer7("احساس همیشگی چربی روی پوست");
                    question.setAnswer8("جوش های سرسیاه و منافذ باز پوست");
                    question.setAnswer9("احساس خشکی گونه ها و چربی بر روی پیشانی، اطراف بینی و چانه");
                    break;

                case 9:
                    question.setQuestion("کدامیک از موارد زیر مسائل و نگرانی اصلی شما در مورد پوست صورت تان در حال حاضر است؟");
                    question.setAnswer1("چروک، تیرگی یا پف دور چشم");
                    question.setAnswer2("چروک و خطوط سطح پوست");
                    question.setAnswer3("خشکی شدید پوست");
                    question.setAnswer4("جوش یا آکنه");
                    question.setAnswer5("لکه های پوستی");
                    question.setAnswer6("منافذ باز و جوش های سرسیاه");
                    break;


                case 10:
                    question.setQuestion("کدامیک از موارد زیر در مورد پوست بدن شما صحیح است؟");
                    question.setAnswer1("خشکی پوست بدن");
                    question.setAnswer2("چرب بودن پوست بدن");
                    question.setAnswer3("جوش های پوستی");
                    question.setAnswer4("اگزما");
                    question.setAnswer5("خشکی و ترک پوست پا");
                    question.setAnswer6("ترک های پوست بدن");
                    break;


                case 11:
                    question.setQuestion("کدامیک از مسائل زیر را در مورد ناخن خود تجربه کرده اید؟");
                    question.setAnswer1("وجود فرورفتگی یا شیار در سطح ناخن");
                    question.setAnswer2("لایه لایه شدن، شکننده بودن یا ترک ناخن");
                    question.setAnswer3("مات بودن یا کدر بودن ناخن");
                    question.setAnswer4("خشکی و جداشدن پوست دور ناخن");
                    break;


                case 12:
                    question.setQuestion("تاکنون از کدامیک از محصولات مراقبتی مو استفاده نموده اید؟");
                    question.setAnswer1("نرم کننده و حالت دهنده");
                    question.setAnswer2("شامپو ضد ریزش");
                    question.setAnswer3("شامپو ضدشوره");
                    question.setAnswer4("ماسک موی آبکشی");
                    question.setAnswer5("ماسک موی بدون آبکشی");
                    question.setAnswer6("سرم مو");
                    break;


                case 13:
                    question.setQuestion("بطور متناوب از کدامیک از موارد زیر استفاده می کنید؟");
                    question.setAnswer1("رنگ مو");
                    question.setAnswer2("دکلره");
                    question.setAnswer3("اتومو یا سشوار گرم");
                    break;


                case 14:
                    question.setQuestion("کدامیک از موارد زیر در مورد موی سر شما صحیح است؟");
                    question.setAnswer1("خشکی پوست سر");
                    question.setAnswer2("چرب بودن پوست سر");
                    question.setAnswer3("خارش پوست سر");
                    question.setAnswer4("خشکی موی سر");
                    question.setAnswer5("عدم حالت پذیری موی سر");
                    question.setAnswer6("وز بودن مو");
                    question.setAnswer7("موخوره");
                    question.setAnswer8("ریزش مو");
                    question.setAnswer9("پوسته یا شوره سر");
                    break;


                case 15:
                    question.setQuestion("کدامیک از موارد زیر در مورد شما صادق است؟");
                    question.setAnswer1("عطش فرآوان");
                    question.setAnswer2("احساس گرما");
                    question.setAnswer3("قدرت هاضمه خوب");
                    question.setAnswer4("احساس تشنگی کم");
                    question.setAnswer5("احساس سردی");
                    question.setAnswer6("قدرت هاضمه کم )ترش کردن معده و ...(");
                    break;


                case 16:
                    question.setQuestion("در مورد انتخاب محصولات مراقبتی پوست و مو کدامیک از موارد زیر را مدنظر می گیرید؟");
                    question.setAnswer1("قیمت");
                    question.setAnswer2("تنوع");
                    question.setAnswer3("برندهای معتبر");
                    question.setAnswer4("کلاسیک و باکیفیت");
                    question.setAnswer5("مدرن و مطابق مد روز");
                    question.setAnswer6("پک بودن محصولات");
                    break;


                case 17:
                    question.setQuestion("در صورت تمایل به سوالات زیر پاسخ دهید:");
                    question.setAnswer1("آیا قبلا از محصولات شرکت ایمان استفاده نموده اید؟");
                    question.setAnswer2("آیا تمایل دارید بعنوان مشاور با نفیس همکاری کنید؟");
                    question.setAnswer3("آیا تمایل دارید با دریافت کد اشتراک از تخفیف مصرف شخصی برخوردار شوید؟");
                    break;

            }
            questions.add(question);

        }
        return questions;
    }
    
    public static List<Category> getCategories(){
        
        List<Category> categories=new ArrayList<>();

        for (int i = 1; i <= 7; i++) {

            Category category = new Category();
            category.setId(i);
            switch (i){

                case 1:
                    category.setName("آروماتیک Aromatic");
                    category.setCount("0");
                    category.setDescription("اولین برند تخصصی آروماتراپی (رایحه درمانی)");
                    category.setImage("http://nafis-app.ir/wp-content/uploads/2018/12/banner_category_aromatic.jpg");
                    category.setSlug("aromatic");
                    break;

                case 2:
                    category.setName("ام ان دی MND");
                    category.setCount("0");
                    category.setDescription("محصولات تخصصی مراقبت از پوست و مو");
                    category.setImage("http://nafis-app.ir/wp-content/uploads/2018/12/banner_category_mnd.jpg");
                    category.setSlug("mnd");
                    break;

                case 3:
                    category.setName("باراکا Baraka");
                    category.setCount("0");
                    category.setDescription("محصولات آرایشی با فرمولاسیون و ساخت شرکت chromavis ایتالیا");
                    category.setImage("http://nafis-app.ir/wp-content/uploads/2018/12/banner_category_baraka.jpg");
                    category.setSlug("baraka");
                    break;

                case 4:
                    category.setName("بلک بری blackberry");
                    category.setCount("0");
                    category.setDescription("اولین برند مراقبتی پوست و مو بر اساس سلول های بنیادی");
                    category.setImage("http://nafis-app.ir/wp-content/uploads/2018/12/banner_category_blackberry.jpg");
                    category.setSlug("blackberry");
                    break;

                case 5:
                    category.setName("پرتسوم Pratsom");
                    category.setCount("0");
                    category.setDescription("محصولات تخصصی پوست و مو");
                    category.setImage("http://nafis-app.ir/wp-content/uploads/2018/12/banner_category_prastom.jpg");
                    category.setSlug("prastom");
                    break;

                case 6:
                    category.setName("تاپ شاپ Top Shop");
                    category.setCount("0");
                    category.setDescription("محصولات پوست و مو با روغن های گیاهی");
                    category.setImage("http://nafis-app.ir/wp-content/uploads/2018/12/banner_category_topShop.jpg");
                    category.setSlug("topshop");
                    break;

                case 7:
                    category.setName("سوپراستار Super Star");
                    category.setCount("0");
                    category.setDescription("محصولات بهداشتی پوست، مو و بدن");
                    category.setImage("http://nafis-app.ir/wp-content/uploads/2018/12/banner_category_superstar.jpg");
                    category.setSlug("superstar");
                    break;

            }
            categories.add(category);
            
        }
        
        return categories;
    }
}
