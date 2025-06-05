
import React, { useState } from 'react';
import { Search as SearchIcon, MapPin, Sliders } from 'lucide-react';
import { Input } from '@/components/ui/input';
import { Button } from '@/components/ui/button';
import { Card, CardContent } from '@/components/ui/card';
import { Badge } from '@/components/ui/badge';

const Search = () => {
  const [searchQuery, setSearchQuery] = useState('');

  const recentSearches = ['Bakery', 'Vegan food', 'Pastries', 'Healthy meals'];
  const popularCategories = [
    { name: 'Bakery', count: 23, icon: '🥖' },
    { name: 'Café', count: 15, icon: '☕' },
    { name: 'Restaurant', count: 31, icon: '🍽️' },
    { name: 'Supermarket', count: 8, icon: '🛒' },
  ];

  return (
    <div className="min-h-screen bg-gray-50">
      {/* Header */}
      <div className="bg-white p-4 pt-8 border-b">
        <div className="flex items-center gap-3 mb-4">
          <div className="relative flex-1">
            <SearchIcon className="absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400" size={20} />
            <Input 
              value={searchQuery}
              onChange={(e) => setSearchQuery(e.target.value)}
              placeholder="Search food, restaurants, categories..." 
              className="pl-10 pr-12"
            />
          </div>
          <Button variant="outline" size="sm">
            <Sliders size={18} />
          </Button>
        </div>
        
        <div className="flex items-center gap-2 text-sm text-gray-600">
          <MapPin size={16} />
          <span>Searching in Chisinau</span>
          <Button variant="link" size="sm" className="p-0 h-auto text-green-600">
            Change
          </Button>
        </div>
      </div>

      <div className="p-4">
        {/* Recent Searches */}
        <div className="mb-6">
          <h2 className="text-lg font-semibold text-gray-900 mb-3">Recent Searches</h2>
          <div className="flex flex-wrap gap-2">
            {recentSearches.map((search, index) => (
              <Badge
                key={index}
                variant="outline"
                className="cursor-pointer hover:bg-gray-100"
                onClick={() => setSearchQuery(search)}
              >
                {search}
              </Badge>
            ))}
          </div>
        </div>

        {/* Popular Categories */}
        <div className="mb-6">
          <h2 className="text-lg font-semibold text-gray-900 mb-3">Popular Categories</h2>
          <div className="grid grid-cols-2 gap-3">
            {popularCategories.map((category) => (
              <Card key={category.name} className="cursor-pointer hover:shadow-md transition-shadow">
                <CardContent className="p-4">
                  <div className="flex items-center justify-between">
                    <div>
                      <div className="text-2xl mb-1">{category.icon}</div>
                      <h3 className="font-medium text-gray-900">{category.name}</h3>
                      <p className="text-sm text-gray-500">{category.count} available</p>
                    </div>
                  </div>
                </CardContent>
              </Card>
            ))}
          </div>
        </div>

        {/* Search Tips */}
        <Card className="bg-green-50 border-green-200">
          <CardContent className="p-4">
            <h3 className="font-medium text-green-800 mb-2">💡 Search Tips</h3>
            <ul className="text-sm text-green-700 space-y-1">
              <li>• Try searching by cuisine type (Italian, Asian, etc.)</li>
              <li>• Filter by pickup time to find immediate availability</li>
              <li>• Use location filters to find food near specific areas</li>
            </ul>
          </CardContent>
        </Card>
      </div>
    </div>
  );
};

export default Search;
