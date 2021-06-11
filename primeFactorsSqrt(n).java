public static List<Integer> primeFactors(int n)
{
		List<Integer> list = new ArrayList<>();
    while (n%2==0)
    {
        list.add(2);
        n /= 2;
    }

    for (int i = 3; i*i<= n; i+= 2)
    {
        while (n%i == 0)
        {
            list.add(i);
            n /= i;
        }
    }

    if (n > 2)
        list.add(n);

    return list;
}
