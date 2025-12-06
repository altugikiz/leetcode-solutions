public class JumpGame {
    public boolean canJump(int[] nums) {
        // Başlangıçta ulaşmak istediğimiz son nokta (Last Index)
        int goal = nums.length - 1;

        // Diziyi sondan başa doğru geziyoruz
        for (int i = nums.length - 2; i >= 0; i--) {

            // "Bulunduğum indeks (i)" + "Zıplama gücüm (nums[i])"
            // eğer hedefime (goal) yetişiyor veya onu geçiyorsa:
            if (i + nums[i] >= goal) {
                // Harika! Demek ki bu noktaya (i) ulaşmam yeterli.
                // Hedef çizgisini buraya çekiyorum.
                goal = i;
            }
        }

        // Eğer hedef çizgisini başa (0. indekse) kadar çekebildiysek
        // oyun kazanılmıştır.
        return goal == 0;
    }
}
