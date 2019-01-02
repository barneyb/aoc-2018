package com.barneyb.aoc2018.day03;

import com.barneyb.aoc2018.util.Histogram;

class Fabric {

    Claim[] claims;
    int width, height;
    Histogram<Integer> claimHistogram;

    Fabric(Claim[] claims) {
        this.claims = claims;
        for (Claim c : claims) {
            width = Math.max(width, c.left + c.width);
            height = Math.max(height, c.top + c.height);
        }
        claimHistogram = buildClaimHistogram();
    }

    int standaloneClaimId() {
        claimLoop:
        for (Claim c : claims) {
            for (int x = c.left, mx = c.left + c.width; x < mx; x++) {
                for (int y = c.top, my = c.top + c.height; y < my; y++) {
                    if (claimHistogram.get(index(x, y)) > 1) {
                        continue claimLoop;
                    }
                }
            }
            return c.id;
        }
        throw new RuntimeException("No claim is isolated");
    }

    int multiClaimedSquares() {
        int mulitClaimCount = 0;
        for (Integer i : claimHistogram.keys()) {
            if (claimHistogram.get(i) > 1) {
                mulitClaimCount += 1;
            }
        }
//            for (int y = 0; y < height; y++) {
//                for (int x = 0; x < width; x++) {
//                    int n = h.get(index(x, y));
//                    System.out.print(n == 0 ? "." : n == 1 ? "c" : "X");
//                }
//                System.out.println();
//            }
        return mulitClaimCount;
    }

    private Histogram<Integer> buildClaimHistogram() {
        Histogram<Integer> h = new Histogram<>();
        for (Claim c : claims) {
            for (int x = c.left, mx = c.left + c.width; x < mx; x++) {
                for (int y = c.top, my = c.top + c.height; y < my; y++) {
                    h.count(index(x, y));
                }
            }
        }
        return h;
    }

    private Integer index(int x, int y) {
        return y * width + x;
    }

}
