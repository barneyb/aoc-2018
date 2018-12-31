package com.barneyb.aoc2018.day23;

import com.barneyb.aoc2018.util.*;

import static com.barneyb.aoc2018.day23.Bot.STEPS;

public class Day23 extends OneShotDay {

    private static final Point3D ORIGIN = new Point3D(0, 0, 0);

    @Override
    public Answers solve(String input) {
        Swarm swarm = Swarm.parse(input);
        return new Answers(
                partOne(swarm)
                , partTwo(swarm)
        );
    }

    static int partOne(Swarm swarm) {
        Bot bot = swarm.strongest();
        int count = 0;
        for (Bot b : swarm.bots) {
            if (bot.inRange(b)) count += 1;
        }
        return count;
    }

    static int partTwo(Swarm swarm) {
        // first, build a graph of bots w/ a signal overlap
        WeightedGraph g = new WeightedGraph(swarm.botCount());
        Bot[] bots = swarm.bots;
        for (int i = 0; i < bots.length; i++) {
            Bot a = bots[i];
            for (int j = i + 1; j < bots.length; j++) {
                Bot b = bots[j];
                if (a.overlaps(b)) {
                    g.addEdge(i, j, a.overlapsBy(b));
                }
            }
        }
        // for small swarms, do some stats
        if (swarm.botCount() < 20) {
            Histogram<Integer> h = new Histogram<>();
            for (int i = 0; i < g.getSiteCount(); i++) {
                h.add(i, g.adjacentCount(i));
                System.out.print("Site " + i + ":");
                for (int j : g.adjacentTo(i)) {
                    System.out.print(" " + j);
                }
                System.out.println();
            }
            System.out.println("Sites: " + g.getSiteCount());
            System.out.println("Edges: " + g.getEdgeCount());
            System.out.println(h.toBarChart());
        }

        // find the largest set of sites which all mutually overlap
        int bestSize = -1;
        Bag<Integer> sites = new Bag<>();
        siteLoop:
        for (int s = 0; s < g.getSiteCount(); s++) {
            int size = g.adjacentCount(s);
            if (size < bestSize) continue;
            Iterable<Integer> adjacencies = g.adjacentTo(s);
            // todo: this double-checks each adjacency
            for (int i : adjacencies) {
                for (int j : adjacencies) {
                    if (i != j && ! g.adjacent(i, j)) continue siteLoop;
                }
            }
            // everything overlaps
            if (size > bestSize) {
                bestSize = size;
                sites.clear();
            }
            // only if it's part of the active "fully connected same sized set"
            if (sites.isEmpty() || g.adjacent(s, sites.iterator().next()))
                sites.add(s);
        }
        System.out.println("Best size: " + bestSize + " (at " + sites.size() + " sites)");
        System.out.println("    " + sites);

        // gather _exactly_ the bots whose ranges overlap
        int aSite = sites.iterator().next();
        TreeSet<Integer> allSites = new TreeSet<>();
        allSites.add(aSite);
        for (Integer s : g.adjacentTo(aSite)) {
            allSites.add(s);
        }

        System.out.println("all sites (" + allSites.size() + "): " + allSites);

        // find the pairs of bots with the smallest overlap "thickness"
        Bag<Vector> bests = new Bag<>();
        int min = Integer.MAX_VALUE;
        for (int s : allSites) {
            for (int i : g.adjacentTo(s)) {
                if (i <= s) continue; // only check one direction
                if (! allSites.contains(i)) continue; // only check candidates
                int w = g.weight(s, i);
//                System.out.println("    Sites " + s + "," + i + " have overlap " + w);
                if (w < min) {
                    min = w;
                    bests.clear();
                }
                if (w == min) {
                    bests.add(new Vector(s, i));
                }
            }
//            System.out.println("  Sites " + s + "," + bestI + " have overlap " + min);
        }
        System.out.println("Site pairs with overlap " + min + " (" + bests.size() + "):");
        System.out.println("  " + bests);

        // so at this point, we've got a list of all minimally thick overlaps
        // in terms of the two bots who create it, within the set of all bots
        // that are in signal range of the solution points

        Vector best = bests.iterator().next();
        int bestS = best.dim(0), bestI = best.dim(1);

        Bot a = bots[bestS];
        System.out.println(a);
        Bot b = bots[bestI];
        System.out.println(b);
        System.out.println("  overlap: " + a.overlapsBy(b));
        Vector mid = a.midpoint(b);
        System.out.println(" midpoint: " + mid);
        System.out.println("     in a: " + a.inRange(mid) + " (" + a.pos.md(mid) + ")");
        System.out.println("     in b: " + b.inRange(mid) + " (" + b.pos.md(mid) + ")");
        System.out.println(" distance: " + mid.md(ORIGIN));

        return partTwo_b(swarm, allSites, bests);
    }

    static int partTwo_entry(Swarm swarm) {
        Bag<Vector> pairs = new Bag<>();
        Scanner s = new Scanner("[(440,502),(329,851),(329,698),(329,446),(283,952),(283,854),(283,820),(283,670),(283,385),(283,358),(243,502),(225,283),(212,502),(209,283),(196,851),(196,698),(196,446),(146,502),(124,283),(86,283),(51,283),(28,283),(10,851),(10,698),(10,446),(7,283)]");
        s.skip(1);
        while (s.probe('(')) {
            pairs.add(Vector.scan(s));
            s.skip(1); // ',' or ']'
        }
        TreeSet<Integer> allSites = new TreeSet<>();
        for (int i : new int[] {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,118,119,120,121,122,123,124,125,126,127,129,130,131,132,133,134,135,136,137,138,139,140,141,142,143,144,145,146,147,148,149,150,151,152,153,154,155,156,157,158,159,160,161,162,163,164,165,166,167,168,169,170,171,172,173,174,175,177,178,179,180,181,182,183,184,185,186,187,188,189,190,191,192,193,194,195,196,197,198,199,200,201,202,203,204,205,206,207,208,209,210,211,212,213,215,216,217,219,220,221,222,223,224,225,226,227,228,229,230,231,232,233,234,235,236,237,238,239,240,241,242,243,244,245,246,247,248,249,250,251,252,253,254,255,256,257,258,259,260,261,262,263,264,265,266,267,268,269,270,271,272,274,276,277,278,279,280,281,282,283,284,285,286,287,288,289,291,292,293,294,296,297,298,299,300,301,302,303,304,305,306,307,308,309,310,311,313,314,315,316,317,318,319,320,321,322,323,324,325,326,327,328,329,330,331,332,333,334,335,336,337,338,339,340,341,342,343,344,346,347,348,349,350,351,352,353,354,355,356,357,358,359,360,361,362,363,364,365,366,367,368,369,370,371,373,374,375,376,377,378,379,380,381,382,383,384,385,386,387,388,389,390,391,392,393,394,395,396,397,399,400,401,402,403,404,405,406,407,408,409,410,411,412,413,414,415,416,417,418,419,420,421,422,423,424,425,426,427,428,429,430,431,432,434,435,436,437,438,439,440,441,442,443,444,445,446,447,448,449,450,451,452,453,454,455,456,457,458,459,460,462,463,464,465,466,467,468,469,470,471,472,473,474,475,476,477,478,479,480,481,482,483,484,485,486,487,488,489,490,491,492,493,494,495,496,497,498,499,500,501,502,503,504,505,506,507,508,509,510,511,512,513,514,515,516,517,518,519,520,521,522,523,524,525,526,527,528,529,530,531,532,533,534,535,536,537,538,539,540,541,542,543,544,545,546,547,548,549,550,551,552,553,554,555,556,557,558,559,560,561,562,563,564,565,566,567,568,569,570,571,572,573,574,575,576,577,578,579,580,581,582,583,584,585,586,587,588,589,590,591,592,593,594,595,597,598,599,600,601,602,603,604,605,606,607,608,609,610,611,612,613,614,615,616,617,618,619,620,621,622,623,624,625,626,627,628,629,630,631,632,634,636,637,638,639,640,641,642,643,644,645,646,647,648,649,650,651,652,653,654,655,656,657,658,659,660,661,662,663,664,665,666,667,668,669,670,671,672,673,674,675,676,677,678,679,680,681,682,683,684,685,686,687,688,689,690,691,692,693,694,695,696,697,698,699,700,701,702,703,704,705,706,707,708,709,710,711,712,713,714,715,716,717,718,719,720,721,722,723,724,725,726,727,728,729,730,731,732,733,734,735,736,737,738,740,741,742,743,744,745,746,747,748,749,750,751,752,753,754,755,756,757,758,759,760,761,762,763,764,765,766,767,768,769,770,771,772,773,774,775,776,777,778,779,780,781,782,783,784,785,786,787,788,789,790,791,792,793,794,795,796,797,798,799,800,801,802,803,804,805,806,807,808,809,810,811,812,813,814,815,816,817,818,819,820,821,822,823,824,825,826,827,828,829,830,831,832,833,834,835,836,837,838,839,840,841,842,843,844,845,846,847,848,849,850,851,852,853,854,855,856,857,858,859,860,861,862,863,864,865,866,867,868,869,870,871,872,873,874,876,877,878,879,881,882,883,884,885,886,888,889,890,891,892,893,894,895,896,897,898,899,900,901,902,903,904,905,906,907,908,909,910,911,912,913,914,915,916,918,919,920,921,922,923,924,925,926,927,928,929,930,931,932,933,934,935,936,937,938,939,940,941,942,943,944,945,947,948,949,950,951,952,953,954,955,956,957,958,959,960,961,962,963,964,965,966,967,968,969,970,971,972,973,974,975,976,977,978,979,980,981,982,983,984,985,986,987,988,989,990,991,992,993,994,995,996,997,998,999}) {
            allSites.add(i);
        }
        return partTwo_b(swarm, allSites, pairs);
    }

    static int partTwo_b(Swarm swarm, TreeSet<Integer> allSites, Bag<Vector> pairs) {
        // todo: this screws sites? i think?
        Bot[] bots = new Bot[allSites.size()];
        int i = 0;
        for (int s : allSites) {
            bots[i++] = swarm.bots[s];
        }
        Vector maxPair = null;
        double max = Double.MAX_VALUE;
        for (Vector pair : pairs) {
            Bot a = bots[pair.dim(0)];
            Bot b = bots[pair.dim(1)];
            int dx = Math.abs(a.pos.x() - b.pos.x());
            int dy = Math.abs(a.pos.y() - b.pos.y());
            int dz = Math.abs(a.pos.z() - b.pos.z());
            int r = a.range + b.range;
            double fx = 1.0 * dx / r;
            double fy = 1.0 * dy / r;
            double fz = 1.0 * dz / r;

            // todo: put this back?
            double fMax = Math.max(fx, Math.max(fy, fz));
            if (r < max) {
                max = r;
                maxPair = pair;
            }
        }
        assert maxPair != null;
        Bot a, b;
        TreeSet<Vector> intersect = null;

        System.out.println("Maxed:");
        a = bots[maxPair.dim(0)];
        System.out.println(a);
        b = bots[maxPair.dim(1)];
        System.out.println(b);
        Vector start = a.midpoint(b);
        for (int s = 10000; s >= 1; s /= 10) {
            intersect = intersection(start, bots, s);
            start = intersect.min();
            System.out.printf("w/ pitch %,d, intersection: %d points:%n", s, intersect.size());
            int cutoff = 0;
            for (Vector p : intersect) {
                System.out.printf("  %s : %,d%n", p, ORIGIN.md(p));
                if (++cutoff == 20) break;
            }
        }

        if (intersect.size() == 1) return ORIGIN.md(intersect.min());
        return -1;
    }

    static TreeSet<Vector> intersection(Vector start, Bot[] bots, int stepSize) {
        TreeSet<Vector> points = new TreeSet<>();
        TreeSet<Vector> visited = new TreeSet<>();
        Queue<Vector> queue = new Queue<>();
        queue.enqueue(start);
        int bestCount = 2;
        Vector[] steps = new Vector[STEPS.length];
        for (int i = 0; i < steps.length; i++) {
            steps[i] = STEPS[i].times(stepSize);
        }
        while (! queue.isEmpty()) {
            Vector p = queue.dequeue();
            if (points.contains(p)) continue;
            int count = 0;
            for (Bot b : bots) {
                if (b.inRange(p)) {
                    count += 1;
                }
            }
            if (count < bestCount - 2) continue; // leave two (not one), because of the 3-d manhattan distance wobble
            if (count > bestCount) {
                bestCount = count;
                System.out.printf("new best: %d (of %d), old points: %,d%n", bestCount, bots.length, points.size());
                points.clear();
            }
            if (count == bestCount) {
                points.add(p);
            }
            for (Vector s : steps) {
                Vector n = p.plus(s);
                if (visited.contains(n)) continue;
                visited.add(n);
                queue.enqueue(n);
            }
        }
        System.out.printf("queued: %,d, best: %d (of %d), points: %,d, visited: %,d%n", queue.size(), bestCount, bots.length, points.size(), visited.size());
        return points;
    }

    // 138,697,281

    public static void main(String[] args)  {
        String input = FileUtils.readFile("day23/input.txt");
        Stopwatch watch = new Stopwatch();
        Swarm swarm = Swarm.parse(input);
        int dist = Day23.partTwo_entry(swarm);
        long e = watch.stop();
        System.out.printf("GLERGGGGGGG: %s in %,d ms%n", dist, e);
    }

}
