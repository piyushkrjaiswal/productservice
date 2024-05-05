# productservice
Java backend project for basic ecommerce platform

Cron Schedule Format:
*    *    *     *    *      *
sec  min  hour  day  month  dayofweek

0 0 9 * * *  everydat at 9:00 A.m.
0 30 9 * * * everyday at 9:30 A.m.
0 30 9 * * 1 every monday at 9:30a.m.

0 * * * * * run every min post deployment
* * * * * * run every second post deployment
0 0 * * * * run every hour post deployment

